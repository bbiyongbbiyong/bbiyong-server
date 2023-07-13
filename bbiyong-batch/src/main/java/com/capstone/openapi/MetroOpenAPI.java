package com.capstone.openapi;

import com.capstone.metro.Metro;
import com.capstone.metro.service.MetroService;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.endpoints.AdditionalParameters;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.UserV2;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetroOpenAPI {

        private final MetroService metroService;

        @Value("${app.twitterAccessToken}")
        private String ACCESS_TOKEN;

        @Value("${app.twitterAccessTokenSecret}")
        private String ACCESS_TOKEN_SECRET;

        @Value("${app.twitterApiKey}")
        private String API_KEY;

        @Value("${app.twitterApiSecretKey}")
        private String API_SECRET_KEY;

        // 트위터 등록정보
        private final TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken("1589104619552022528-n3nCNNaegfh1L0PJDMC8PPnb06ktfC")
                .accessTokenSecret("HB0zsGj4vKmrCx4oONMv0VXPMX40gLGbQEWtPsKkSGCHY")
                .apiKey("NyELrY5Ow6EvtkBqpzjhbtfNi")
                .apiSecretKey("9XxENIHHT5qmt5huEWFN00tLRo4aRmC7ES64klTtV69GdZ1E7X")
                .build());

        public void callOpenAPI() {
                LocalDateTime endLocalDateTime = LocalDateTime.now();
                LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(3);
                // 파라메터 설정
                AdditionalParameters additionalParameters = AdditionalParameters.builder()
                        .startTime(startLocalDateTime)
                        .endTime(endLocalDateTime)
                        .build();

                UserV2 userV2 = twitterClient.getUserFromUserName("seoul_metro");

                TweetList tweetList = twitterClient.getUserTimeline(userV2.getId(), additionalParameters);

                for(TweetV2.TweetData tweet : tweetList.getData()) {

                        String strId = tweet.getId();
                        long openapiId = Long.parseLong(strId);
                        String text = tweet.getText();
                        String result = processText(text);
                        LocalDateTime startDateTime = tweet.getCreatedAt();
                        LocalDateTime endDateTime = startDateTime.plusDays(2);

                        Metro metro = Metro.builder()
                                .openapiId(openapiId)
                                .text(result)
                                .startDate(startDateTime)
                                .endDate(endDateTime)
                                .build();

                        metroService.addMetro(metro);
                }
        }

        public String processText(String text) {
                String result;

                if (text.contains("안내말씀 드립니다.\n") && text.contains("\n열차 이용에 참고하시기 바랍니다."))
                        result = text.substring(12, text.lastIndexOf("\n"));
                else if (text.contains("안내말씀 드립니다.\n") && (text.contains(" 열차 이용에 참고하여 주시기 바랍니다.") || text.contains(" 열차 이용에 참고하시기 바랍니다.")))
                        result = text.substring(12, text.lastIndexOf(". ") + 1);
                else if (text.contains("안내말씀 드립니다.\n"))
                        result = text.substring(12);
                else if (text.contains("\n열차 이용에 참고하시기 바랍니다."))
                        result = text.substring(0, text.lastIndexOf("\n"));
                else if (text.contains(" 열차 이용에 참고하여 주시기 바랍니다."))
                        result = text.substring(0, text.lastIndexOf(". ") + 1);
                else
                        result = text;

                return result;
        }
}