package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class XMLParser {

    public List<Modules> parseModules(String xml)
    {
        XmlPullParserFactory factory;
        List<Modules> Modules = new ArrayList();
        Modules module = null;
        String text = "";


        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            if (xml == null)
            {
                return null;

            }

            parser.setInput( new StringReader( xml ) );
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:

                        if (tag.equalsIgnoreCase("modules"))
                        {
                           module = new Modules();
                        }

                    case XmlPullParser.TEXT:

                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if (tag.equalsIgnoreCase("username"))
                        {
                            module.setUsername(text);
                            Modules.add(module);

                        }

                        if (tag.equalsIgnoreCase("endorsements"))
                        {
                            module.setEndorsements(Integer.parseInt(text));
                        }

                        break;
                }
                eventType = parser.next();
            }
            return Modules;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
