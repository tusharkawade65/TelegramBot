package Rajas.com.botRest.BotRest.Service;

import Rajas.com.botRest.BotRest.Entity.CategoryModel;
import Rajas.com.botRest.BotRest.Entity.ProductModel;
import Rajas.com.botRest.BotRest.NLP.Lemmatization;
import Rajas.com.botRest.BotRest.NLP.Tokenize;
import Rajas.com.botRest.BotRest.RecklerBot;
import Rajas.com.botRest.BotRest.Repository.CategoryRepository;
import Rajas.com.botRest.BotRest.Repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ItemService {

//nlp
    Lemmatization lemmatization = new Lemmatization();
    Tokenize tokenize = new Tokenize();
    private CategoryRepository categoryRepository;

    Logger logger = Logger.getLogger(RecklerBot.class.getSimpleName());

    public String getCategories(CategoryRepository categoryRepository) {
        logger.info("returning all categories");
        StringBuilder categoryList =  new StringBuilder("All Categories" + "\n" + "\n");

        List<CategoryModel> categories = categoryRepository.findAll();
        categoryList.append(categories.stream().map(c->c.getCatId()+". "+c.getCategory()+"\n").collect(Collectors.joining()));
        return new String(categoryList);
    }

    public int getCategoryIdByCategory(String category, CategoryRepository categoryRepository) {
        logger.info("returning category id by category");
        return categoryRepository.getCategoryId(category);
    }

    public String getProducts(ProductRepository productRepository, int catId) {
        LinkedList<ProductModel> products = productRepository.getProductsByCategoryId(catId);
        String productDetails = "";
        for (ProductModel productModel : products) {
            productDetails = productDetails.concat("-> ");
            productDetails = productDetails.concat(productModel.getName() + "   " + "₹" + productModel.getPrice() + "/-" + "\n");

        }
        return productDetails;

    }
    public List<String> getProductsListByCategory(ProductRepository productRepository, int catId) {
        logger.info("returning products by category");
        List<ProductModel> products = productRepository.getProductsByCategoryId(catId);
        List<String> productNameList = products.stream().map(ProductModel::getName).toList();

        return productNameList;

    }




    public List<String> getCategoryList(CategoryRepository categoryRepository)
    {
        List<CategoryModel> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryModel::getCategory).toList();
    }

    public String recogniseCategoryByName(String command, List<CategoryModel> categories) {
        //nlp
        System.out.println(command +"commandd");
        String rootString = lemmatization.getLemma(command);
        String newCommand = tokenize.tokenization(rootString);

        for (int i = 0; i < categories.size(); i++) {
            if (newCommand.contains(categories.get(i).getCategory())) {
                return categories.get(i).getCategory();
            }
        }
        return null;
    }

    public boolean recogniseCategory(String command) {
        //nlp
        String rootString = lemmatization.getLemma(command);
        String newCommand = tokenize.tokenization(rootString);

        if (newCommand.contains("Category")) {
            return true;
        } else {
            return false;
        }

    }
// returns only one product which is matching with user command
    public LinkedList<ProductModel> productModels(String command, ProductRepository productRepository) {

        int i = 0;
        LinkedList<ProductModel> productByName = null;
        //nlp
        String rootString = lemmatization.getLemma(command);
//        String newCommand = tokenize.tokenization(rootString);

        String[] meaningfulWords = rootString.split(" ");
        String[] arr = new String[meaningfulWords.length];

        for (i = 0; i < meaningfulWords.length; i++) {
            if (meaningfulWords[i].length() > 2) {
                arr[i] = meaningfulWords[i];
            }
        }
//
        for (i = 0; i < arr.length; i++) {
            if (productRepository.findByNameContaining(arr[i]).size() != 0) {

                productByName = productRepository.findByNameContaining(arr[i]);
                break;
//
            }
        }
//
        return productByName;
    }

    public String stringConverterForProductList(LinkedList<ProductModel> productModels) {
        int i = 0;
        String products="";
        if(productModels!=null) {
            for (ProductModel productModel : productModels) {
                products = products.concat("<b>");
                products = products.concat(productModels.get(i).getName() + "</b>\t" + "₹");
                products = products.concat(String.valueOf(productModels.get(i).getPrice()) + "\n");
                products = products.concat("Desc: " + productModels.get(i).getDescription() + "\n" + "\n");
                i++;
            }
        }
        return products;
    }

    public int stringToIntConverter(String command) {
        int number = 0;
        try {
            number = Integer.parseInt(command);
        } catch (Exception e) {
        }
        return number;
    }

    public String getProductsByCategoryId(ProductRepository productRepository, int id) {
        String products = getProducts(productRepository, id);
        return products;
    }

    public boolean recogniseAddToCart(String command) {


        //nlp
        String rootString = lemmatization.getLemma(command);
        String newCommand = tokenize.tokenization(rootString);
//        String newCommand=command;
        if ((newCommand.contains("Put")) || (newCommand.contains("Insert")) || (newCommand.contains("Add"))&&(newCommand.contains("Cart"))) {
            return true;
        } else {
            return false;
        }

    }
public int flag=0;
    public String productByButton(String command,ProductRepository productRepository){
        LinkedList<ProductModel>productModels= productRepository.findByNameEquals(command);
        String productName ="";
        if(productModels.isEmpty())
        {

        }
        else {
            productName = productModels.get(0).getName();
        }
        if (productName.length()>2)
        {
            flag=1;
            return productName;
        }
        else {
            flag=0;
            ButtonServiceForProducts buttonService = new ButtonServiceForProducts();
            LinkedList<ButtonServiceForProducts> buttonServiceForProducts = new LinkedList<>();
            LinkedList<ProductModel> productModelLinkedList = productModels(command,productRepository);


           return stringConverterForProductList(productModelLinkedList);


        }



    }
    public String formattedString(String prodName,String prodDescription,int prodPrice){
        String formatString = ""+prodName +"\n"+"₹ "+ prodPrice +"\n"+prodDescription;
        return formatString;
    }
}
