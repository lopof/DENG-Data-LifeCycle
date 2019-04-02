/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.websocket;

import org.apache.camel.main.Main;
import com.cybozu.labs.langdetect.*;


/**
 * A main to start this example.
 */
public final class CamelTwitterWebSocketMain {

    // Twitter now requires the use of OAuth for all client application authentication.
    // In order to use camel-twitter with your account, you'll need to create a new application
    // within Twitter at https://dev.twitter.com/apps/new and grant the application access to your account.
    // Finally, generate your access token and secret.

    // This uses the Twitter 'cameltweet' account for testing purposes.
    // do NOT use this twitter account in your applications!
    private static String consumerKey = "3XmdSRnIwQHRQTFzScaj2Hbm3";
    private static String consumerSecret = "aNOehkntPBN2pEDNu5fktW1vMT14WmG0bFf83OD91h0NCcU7nV";
    private static String accessToken = "939207014319906817-AqzrU2pRc4HY8WyV3WJwKa9cUGUkcZq";
    private static String accessTokenSecret = "4Lx4D3M65Dj6x1ADtuSW7Rgnm7UgTS2BAHe9aohpXr8Vx";

    private CamelTwitterWebSocketMain() {
        // to pass checkstyle we have a private constructor
    }


    public static void main(String[] args) throws Exception {
        System.out.println("\n\n\n\n");
        System.out.println("===============================================");
        System.out.println("Open your web browser on http://localhost:9090/index.html");
        System.out.println("Press ctrl+c to stop this example");
        System.out.println("===============================================");
        System.out.println("\n\n\n\n");


        LangDetect lang = new LangDetect();
        lang.init("/Users/tom/Documents/BFH/6.Semester/DataEngineering/langdetect-09-13-2011/profiles");

        System.out.println(lang.detect("Deutschland ist schön"));

        // create a new Camel Main so we can easily start Camel
        Main main = new Main();

        TwitterWebSocketRoute route = new TwitterWebSocketRoute();

        // setup twitter application authentication
        route.setAccessToken(accessToken);
        route.setAccessTokenSecret(accessTokenSecret);
        route.setConsumerKey(consumerKey);
        route.setConsumerSecret(consumerSecret);

        // poll for gaga, every 5nd second
        // twitter rate limits 180 per 15 min, so that is 0.2/sec, eg 1/5sec.
        // so to be safe we do 6 seconds
        route.setSearchTerm("Rammstein");

        route.setDelay(6000);

        // web socket on port 9090
        route.setPort(9090);

        // add our routes to Camel
        main.addRouteBuilder(route);

        // and run, which keeps blocking until we terminate the JVM (or stop CamelContext)
        main.run();
    }

}
