package com.example.ian.top10downloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by ian on 15/05/2017.
 */

public class ParseApplications {
    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    public ParseApplications() {
        this.applications = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    /**
     *
     * @param xmlData XML data downloaded from the rss feed
     * @return boolean to state whether parsing is successful or not
     */
    public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        //inEntry ensures that we only get data when we are inside an entry of the rssFeed
        boolean inEntry = false;
        //textValue stores the current value inside the tag
        String textValue = "";

        try {
            //https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();

            //Keep looping until end of document is reach
            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    //Gets the tag i.e. entry, id, title, summary
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();
                        }
                        break;

                    //Gets the value between the start and end tag
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    //Saves the text to the current record based on the tag
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for " + tagName);
                        if(inEntry) {
                            if("entry".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if("name".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if("artist".equalsIgnoreCase(tagName)) {
                                currentRecord.setArtist(textValue);
                            } else if("releaseDate".equalsIgnoreCase(tagName)) {
                                currentRecord.setReleaseDate(textValue);
                            } else if("summary".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);
                            } else if("image".equalsIgnoreCase(tagName)) {
                                currentRecord.setImageURL(textValue);
                            }
                        }
                        break;

                    default:
                        // Nothing else to do.
                }
                eventType = xpp.next();

            }

        } catch(Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}
