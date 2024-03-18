package Rajas.com.botRest.BotRest.NLP;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@RequiredArgsConstructor
public class pipeline {


    private static Properties properties;
    private static String propertiesName ="tokenize,ssplit,pos,lemma,ner,parse,sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;




    static {
        properties = new Properties();
        properties.setProperty("annotators",propertiesName);
    }
    public static StanfordCoreNLP getPipeline(){
        if(stanfordCoreNLP==null){
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
