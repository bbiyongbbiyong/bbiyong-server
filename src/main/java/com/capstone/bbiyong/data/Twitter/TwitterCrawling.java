package com.capstone.bbiyong.data.Twitter;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.endpoints.AdditionalParameters;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.UserV2;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class TwitterCrawling {

        @Value("${app.twitterAccessToken}")
        private static String ACCESS_TOKEN;

        @Value("${app.twitterAccessTokenSecret}")
        private static String ACCESS_TOKEN_SECRET;

        @Value("${app.twitterApiKey}")
        private static String API_KEY;

        @Value("${app.twitterApiSecretKey}")
        private static String API_SECRET_KEY;

        // 트위터 등록정보
        private static TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken("1589104619552022528-n3nCNNaegfh1L0PJDMC8PPnb06ktfC")
                .accessTokenSecret("HB0zsGj4vKmrCx4oONMv0VXPMX40gLGbQEWtPsKkSGCHY")
                .apiKey("NyELrY5Ow6EvtkBqpzjhbtfNi")
                .apiSecretKey("9XxENIHHT5qmt5huEWFN00tLRo4aRmC7ES64klTtV69GdZ1E7X")
                .build());

        public static void TestTwitterLoading() {
                LocalDateTime endLocalDateTime = LocalDateTime.now();
                LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(7);
                // 파라메터 설정
                AdditionalParameters additionalParameters = AdditionalParameters.builder()
                        .startTime(startLocalDateTime)
                        .endTime(endLocalDateTime)
                        .build();

                UserV2 userV2 = twitterClient.getUserFromUserName("seoul_metro");
                System.out.println("아이디 로딩 체크");
                System.out.println("아이디 : " + userV2.getId());

                TweetList tweetList = twitterClient.getUserTimeline(userV2.getId(), additionalParameters);
                System.out.println("트윗 로딩 체크");
                System.out.println("가져온 트윗 수 : " + tweetList.getData().size());
                for(TweetV2.TweetData tweet : tweetList.getData()) {
                        System.out.println("Id : " + tweet.getId());

                        String text = tweet.getText();
                        String result = "";

                        if (text.contains("안내말씀 드립니다.\n") && text.contains("\n열차 이용에 참고하시기 바랍니다."))
                                result = text.substring(11, text.lastIndexOf("\n"));
                        else if (text.contains("안내말씀 드립니다.\n") && (text.contains(" 열차 이용에 참고하여 주시기 바랍니다.") || text.contains(" 열차 이용에 참고하시기 바랍니다.")))
                                result = text.substring(11, text.lastIndexOf(". ") + 1);
                        else if (text.contains("안내말씀 드립니다.\n"))
                                result = text.substring(11);
                        else if (text.contains("\n열차 이용에 참고하시기 바랍니다."))
                                result = text.substring(0, text.lastIndexOf("\n"));
                        else if (text.contains(" 열차 이용에 참고하여 주시기 바랍니다."))
                                result = text.substring(0, text.lastIndexOf(". ") + 1);
                        else
                                result = text;



                        System.out.println("text : " + result);
                        System.out.println("==============================");


                }
        }

        public static void main(String[] args) {
                TestTwitterLoading();
        }
}