package com.capstone.notification.service;

import com.capstone.common.exception.EntityNotFoundException;
import com.capstone.common.exception.ErrorCode;
import com.capstone.member.domain.Member;
import com.capstone.member.repository.MemberRepository;
import com.capstone.notification.domain.Subscribe;
import com.capstone.notification.dto.*;
import com.capstone.notification.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final SubscribeRepository subscribeRepository;
    private final MemberRepository memberRepository;

    public SubscribeResponseDTO getTopic(Member member) {
        Long memberId = member.getId();
        List<Subscribe> subscribeList = subscribeRepository.findByMemberId(memberId);

        if (subscribeList.isEmpty()) {
            SubscribeRequestDTO requestDTO = new SubscribeRequestDTO(false, new NotificationListDTO(new NaturalDisasterDTO(), new SocialDisasterDTO(), new SubwayDTO(), new RoadControllerDTO()));
            saveTopic(member, requestDTO);
        }

        NaturalDisasterDTO naturalDisasterDTO = NaturalDisasterDTO.of(
                subscribeRepository.findByMemberIdAndTopic(memberId, "typhoon").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "dry").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "forestFires").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "landslide").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "flood").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "downpour").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "heatWave").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "fog").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "windWave").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "fineDust").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "springTide").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "drought").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "heavySnow").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "tsunami").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "earthquake").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "coldWave").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "yellowDust").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "gale").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "naturalEtc").isSubscribe()
        );

        SocialDisasterDTO socialDisasterDTO = SocialDisasterDTO.of(
                subscribeRepository.findByMemberIdAndTopic(memberId, "trafficControl").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "fireAlert").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "collapse").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "explosion").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "trafficAccident").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "envPollution").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "energy").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "communication").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "medical").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "waterAlert").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "epidemic").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "blackout").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "gas").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "missing").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "traffic").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "socialEtc").isSubscribe()
        );

        SubwayDTO subwayDTO = SubwayDTO.of(
                subscribeRepository.findByMemberIdAndTopic(memberId, "line1").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line2").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line3").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line4").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line5").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line6").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line7").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line8").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "line9").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "incheonLine1").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "incheonLine2").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "westcoastLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "geongangLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "airportLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "bundangLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "dxLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "uiLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "yonginLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "ulrtLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "gimpoLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "sillimLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "geongchunLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "gyoungiLine").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "lineEtc").isSubscribe()
        );

        RoadControllerDTO roadControllerDTO = RoadControllerDTO.of(
                subscribeRepository.findByMemberIdAndTopic(memberId, "roadAccident").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "roadWorks").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "rallyEvent").isSubscribe(),
                subscribeRepository.findByMemberIdAndTopic(memberId, "roadEtc").isSubscribe()
        );

        NotificationListDTO notificationListDTO = NotificationListDTO.of(
                naturalDisasterDTO,
                socialDisasterDTO,
                subwayDTO,
                roadControllerDTO
        );

        return new SubscribeResponseDTO(member.isNotifyOn(), notificationListDTO);
    }

    @Transactional
    public void saveTopic(Member member, SubscribeRequestDTO requestDTO) {
        List<Subscribe> subscribeList = subscribeRepository.findByMemberId(member.getId());

        if (subscribeList.isEmpty()) {
            List<Subscribe> subscribes = new ArrayList<>();

            Subscribe typhoon = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("typhoon")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isTyphoon())
                    .member(member)
                    .build();
            subscribes.add(typhoon);

            Subscribe dry = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("dry")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isDry())
                    .member(member)
                    .build();
            subscribes.add(dry);

            Subscribe forestFires = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("forestFires")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isForestFires())
                    .member(member)
                    .build();
            subscribes.add(forestFires);

            Subscribe landslide = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("landslide")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isLandslide())
                    .member(member)
                    .build();
            subscribes.add(landslide);

            Subscribe flood = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("flood")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isFlood())
                    .member(member)
                    .build();
            subscribes.add(flood);

            Subscribe downpour = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("downpour")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isDownpour())
                    .member(member)
                    .build();
            subscribes.add(downpour);

            Subscribe heatWave = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("heatWave")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isHeatWave())
                    .member(member)
                    .build();
            subscribes.add(heatWave);

            Subscribe fog = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("fog")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isFog())
                    .member(member)
                    .build();
            subscribes.add(fog);

            Subscribe windWave = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("windWave")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isWindWave())
                    .member(member)
                    .build();
            subscribes.add(windWave);

            Subscribe fineDust = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("fineDust")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isFineDust())
                    .member(member)
                    .build();
            subscribes.add(fineDust);

            Subscribe springTide = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("springTide")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isSpringTide())
                    .member(member)
                    .build();
            subscribes.add(springTide);

            Subscribe drought = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("drought")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isDrought())
                    .member(member)
                    .build();
            subscribes.add(drought);

            Subscribe heavySnow = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("heavySnow")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isHeavySnow())
                    .member(member)
                    .build();
            subscribes.add(heavySnow);

            Subscribe tsunami = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("tsunami")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isTsunami())
                    .member(member)
                    .build();
            subscribes.add(tsunami);

            Subscribe earthquake = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("earthquake")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isEarthquake())
                    .member(member)
                    .build();
            subscribes.add(earthquake);

            Subscribe coldWave = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("coldWave")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isColdWave())
                    .member(member)
                    .build();
            subscribes.add(coldWave);

            Subscribe yellowDust = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("yellowDust")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isYellowDust())
                    .member(member)
                    .build();
            subscribes.add(yellowDust);

            Subscribe gale = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("gale")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isGale())
                    .member(member)
                    .build();
            subscribes.add(gale);

            Subscribe naturalEtc = Subscribe.builder()
                    .type("naturalDisaster")
                    .topic("naturalEtc")
                    .isSubscribe(requestDTO.getNotificationList().getNaturalDisaster().isNaturalEtc())
                    .member(member)
                    .build();
            subscribes.add(naturalEtc);

            Subscribe trafficControl = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("trafficControl")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isTrafficControl())
                    .member(member)
                    .build();
            subscribes.add(trafficControl);

            Subscribe fireAlert = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("fireAlert")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isFireAlert())
                    .member(member)
                    .build();
            subscribes.add(fireAlert);

            Subscribe collapse = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("collapse")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isCollapse())
                    .member(member)
                    .build();
            subscribes.add(collapse);

            Subscribe explosion = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("explosion")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isExplosion())
                    .member(member)
                    .build();
            subscribes.add(explosion);

            Subscribe trafficAccident = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("trafficAccident")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isTrafficAccident())
                    .member(member)
                    .build();
            subscribes.add(trafficAccident);

            Subscribe envPollution = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("envPollution")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isEnvPollution())
                    .member(member)
                    .build();
            subscribes.add(envPollution);

            Subscribe energy = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("energy")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isEnergy())
                    .member(member)
                    .build();
            subscribes.add(energy);

            Subscribe communication = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("communication")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isCommunication())
                    .member(member)
                    .build();
            subscribes.add(communication);

            Subscribe medical = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("medical")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isMedical())
                    .member(member)
                    .build();
            subscribes.add(medical);

            Subscribe waterAlert = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("waterAlert")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isWaterAlert())
                    .member(member)
                    .build();
            subscribes.add(waterAlert);

            Subscribe epidemic = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("epidemic")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isEpidemic())
                    .member(member)
                    .build();
            subscribes.add(epidemic);

            Subscribe blackout = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("blackout")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isBlackout())
                    .member(member)
                    .build();
            subscribes.add(blackout);

            Subscribe gas = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("gas")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isGas())
                    .member(member)
                    .build();
            subscribes.add(gas);

            Subscribe missing = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("missing")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isMissing())
                    .member(member)
                    .build();
            subscribes.add(missing);

            Subscribe traffic = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("traffic")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isTraffic())
                    .member(member)
                    .build();
            subscribes.add(traffic);

            Subscribe socialEtc = Subscribe.builder()
                    .type("socialDisaster")
                    .topic("socialEtc")
                    .isSubscribe(requestDTO.getNotificationList().getSocialDisaster().isSocialEtc())
                    .member(member)
                    .build();
            subscribes.add(socialEtc);

            Subscribe line1 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line1")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine1())
                    .member(member)
                    .build();
            subscribes.add(line1);

            Subscribe line2 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line2")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine2())
                    .member(member)
                    .build();
            subscribes.add(line2);

            Subscribe line3 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line3")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine3())
                    .member(member)
                    .build();
            subscribes.add(line3);

            Subscribe line4 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line4")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine4())
                    .member(member)
                    .build();
            subscribes.add(line4);

            Subscribe line5 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line5")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine5())
                    .member(member)
                    .build();
            subscribes.add(line5);

            Subscribe line6 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line6")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine6())
                    .member(member)
                    .build();
            subscribes.add(line6);

            Subscribe line7 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line7")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine7())
                    .member(member)
                    .build();
            subscribes.add(line7);

            Subscribe line8 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line8")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine8())
                    .member(member)
                    .build();
            subscribes.add(line8);

            Subscribe line9 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("line9")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLine9())
                    .member(member)
                    .build();
            subscribes.add(line9);

            Subscribe lineEtc = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("lineEtc")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isLineEtc())
                    .member(member)
                    .build();
            subscribes.add(lineEtc);

            Subscribe incheonLine1 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("incheonLine1")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isIncheonLine1())
                    .member(member)
                    .build();
            subscribes.add(incheonLine1);

            Subscribe incheonLine2 = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("incheonLine2")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isIncheonLine2())
                    .member(member)
                    .build();
            subscribes.add(incheonLine2);

            Subscribe westcoastLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("westcoastLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isWestcoastLine())
                    .member(member)
                    .build();
            subscribes.add(westcoastLine);

            Subscribe geongangLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("geongangLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isGeongangLine())
                    .member(member)
                    .build();
            subscribes.add(geongangLine);

            Subscribe airportLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("airportLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isAirportLine())
                    .member(member)
                    .build();
            subscribes.add(airportLine);

            Subscribe bundangLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("bundangLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isBundangLine())
                    .member(member)
                    .build();
            subscribes.add(bundangLine);

            Subscribe dxLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("dxLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isDxLine())
                    .member(member)
                    .build();
            subscribes.add(dxLine);

            Subscribe uiLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("uiLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isUiLine())
                    .member(member)
                    .build();
            subscribes.add(uiLine);

            Subscribe yonginLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("yonginLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isYonginLine())
                    .member(member)
                    .build();
            subscribes.add(yonginLine);

            Subscribe ulrtLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("ulrtLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isUlrtLine())
                    .member(member)
                    .build();
            subscribes.add(ulrtLine);

            Subscribe gimpoLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("gimpoLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isGimpoLine())
                    .member(member)
                    .build();
            subscribes.add(gimpoLine);

            Subscribe sillimLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("sillimLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isSillimLine())
                    .member(member)
                    .build();
            subscribes.add(sillimLine);

            Subscribe geongchunLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("geongchunLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isGeongchunLine())
                    .member(member)
                    .build();
            subscribes.add(geongchunLine);

            Subscribe gyoungiLine = Subscribe.builder()
                    .type("subwayInformation")
                    .topic("gyoungiLine")
                    .isSubscribe(requestDTO.getNotificationList().getSubwayInformation().isGyoungiLine())
                    .member(member)
                    .build();
            subscribes.add(gyoungiLine);

            Subscribe roadAccident = Subscribe.builder()
                    .type("roadControlInformation")
                    .topic("roadAccident")
                    .isSubscribe(requestDTO.getNotificationList().getRoadControlInformation().isRoadAccident())
                    .member(member)
                    .build();
            subscribes.add(roadAccident);

            Subscribe roadWorks = Subscribe.builder()
                    .type("roadControlInformation")
                    .topic("roadWorks")
                    .isSubscribe(requestDTO.getNotificationList().getRoadControlInformation().isRoadWorks())
                    .member(member)
                    .build();
            subscribes.add(roadWorks);

            Subscribe rallyEvent = Subscribe.builder()
                    .type("roadControlInformation")
                    .topic("rallyEvent")
                    .isSubscribe(requestDTO.getNotificationList().getRoadControlInformation().isRallyEvent())
                    .member(member)
                    .build();
            subscribes.add(rallyEvent);

            Subscribe roadEtc = Subscribe.builder()
                    .type("roadControlInformation")
                    .topic("roadEtc")
                    .isSubscribe(requestDTO.getNotificationList().getRoadControlInformation().isRoadEtc())
                    .member(member)
                    .build();
            subscribes.add(roadEtc);

            subscribeRepository.saveAll(subscribes);
        }

        for(int i = 0; i < subscribeList.size(); i++) {
            Subscribe sub = subscribeRepository.findByMemberIdAndTopic(member.getId(), subscribeList.get(i).getTopic());

            if (sub.getTopic().equals("typhoon") && requestDTO.getNotificationList().getNaturalDisaster().isTyphoon() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isTyphoon());
            else if (sub.getTopic().equals("dry") && requestDTO.getNotificationList().getNaturalDisaster().isDry() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isDry());
            else if (sub.getTopic().equals("forestFires") && requestDTO.getNotificationList().getNaturalDisaster().isForestFires() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isForestFires());
            else if (sub.getTopic().equals("landslide") && requestDTO.getNotificationList().getNaturalDisaster().isLandslide() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isLandslide());
            else if (sub.getTopic().equals("flood") && requestDTO.getNotificationList().getNaturalDisaster().isFlood() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isFlood());
            else if (sub.getTopic().equals("downpour") && requestDTO.getNotificationList().getNaturalDisaster().isDownpour() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isDownpour());
            else if (sub.getTopic().equals("heatWave") && requestDTO.getNotificationList().getNaturalDisaster().isHeatWave() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isHeatWave());
            else if (sub.getTopic().equals("fog") && requestDTO.getNotificationList().getNaturalDisaster().isFog() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isFog());
            else if (sub.getTopic().equals("windWave") && requestDTO.getNotificationList().getNaturalDisaster().isWindWave() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isWindWave());
            else if (sub.getTopic().equals("fineDust") && requestDTO.getNotificationList().getNaturalDisaster().isFineDust() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isFineDust());
            else if (sub.getTopic().equals("springTide") && requestDTO.getNotificationList().getNaturalDisaster().isSpringTide() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isSpringTide());
            else if (sub.getTopic().equals("drought") && requestDTO.getNotificationList().getNaturalDisaster().isDrought() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isDrought());
            else if (sub.getTopic().equals("heavySnow") && requestDTO.getNotificationList().getNaturalDisaster().isHeavySnow() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isHeavySnow());
            else if (sub.getTopic().equals("tsunami") && requestDTO.getNotificationList().getNaturalDisaster().isTsunami() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isTsunami());
            else if (sub.getTopic().equals("earthquake") && requestDTO.getNotificationList().getNaturalDisaster().isEarthquake() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isEarthquake());
            else if (sub.getTopic().equals("coldWave") && requestDTO.getNotificationList().getNaturalDisaster().isColdWave() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isColdWave());
            else if (sub.getTopic().equals("yellowDust") && requestDTO.getNotificationList().getNaturalDisaster().isYellowDust() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isYellowDust());
            else if (sub.getTopic().equals("gale") && requestDTO.getNotificationList().getNaturalDisaster().isGale() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isGale());
            else if (sub.getTopic().equals("naturalEtc") && requestDTO.getNotificationList().getNaturalDisaster().isNaturalEtc() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getNaturalDisaster().isNaturalEtc());
            else if (sub.getTopic().equals("trafficControl") && requestDTO.getNotificationList().getSocialDisaster().isTrafficControl() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isTrafficControl());
            else if (sub.getTopic().equals("fireAlert") && requestDTO.getNotificationList().getSocialDisaster().isFireAlert() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isFireAlert());
            else if (sub.getTopic().equals("collapse") && requestDTO.getNotificationList().getSocialDisaster().isCollapse() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isCollapse());
            else if (sub.getTopic().equals("explosion") && requestDTO.getNotificationList().getSocialDisaster().isExplosion() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isExplosion());
            else if (sub.getTopic().equals("trafficAccident") && requestDTO.getNotificationList().getSocialDisaster().isTrafficAccident() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isTrafficAccident());
            else if (sub.getTopic().equals("envPollution") && requestDTO.getNotificationList().getSocialDisaster().isEnvPollution() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isEnvPollution());
            else if (sub.getTopic().equals("energy") && requestDTO.getNotificationList().getSocialDisaster().isEnergy() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isEnergy());
            else if (sub.getTopic().equals("communication") && requestDTO.getNotificationList().getSocialDisaster().isCommunication() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isCommunication());
            else if (sub.getTopic().equals("medical") && requestDTO.getNotificationList().getSocialDisaster().isMedical() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isMedical());
            else if (sub.getTopic().equals("waterAlert") && requestDTO.getNotificationList().getSocialDisaster().isWaterAlert() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isWaterAlert());
            else if (sub.getTopic().equals("epidemic") && requestDTO.getNotificationList().getSocialDisaster().isEpidemic() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isEpidemic());
            else if (sub.getTopic().equals("blackout") && requestDTO.getNotificationList().getSocialDisaster().isBlackout() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isBlackout());
            else if (sub.getTopic().equals("gas") && requestDTO.getNotificationList().getSocialDisaster().isGas() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isGas());
            else if (sub.getTopic().equals("missing") && requestDTO.getNotificationList().getSocialDisaster().isMissing() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isMissing());
            else if (sub.getTopic().equals("traffic") && requestDTO.getNotificationList().getSocialDisaster().isTraffic() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isTraffic());
            else if (sub.getTopic().equals("socialEtc") && requestDTO.getNotificationList().getSocialDisaster().isSocialEtc() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSocialDisaster().isSocialEtc());
            else if (sub.getTopic().equals("line1") && requestDTO.getNotificationList().getSubwayInformation().isLine1() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine1());
            else if (sub.getTopic().equals("line2") && requestDTO.getNotificationList().getSubwayInformation().isLine2() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine2());
            else if (sub.getTopic().equals("line3") && requestDTO.getNotificationList().getSubwayInformation().isLine3() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine3());
            else if (sub.getTopic().equals("line4") && requestDTO.getNotificationList().getSubwayInformation().isLine4() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine4());
            else if (sub.getTopic().equals("line5") && requestDTO.getNotificationList().getSubwayInformation().isLine5() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine5());
            else if (sub.getTopic().equals("line6") && requestDTO.getNotificationList().getSubwayInformation().isLine6() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine6());
            else if (sub.getTopic().equals("line7") && requestDTO.getNotificationList().getSubwayInformation().isLine7() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine7());
            else if (sub.getTopic().equals("line8") && requestDTO.getNotificationList().getSubwayInformation().isLine8() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine8());
            else if (sub.getTopic().equals("line9") && requestDTO.getNotificationList().getSubwayInformation().isLine9() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine9());
            else if (sub.getTopic().equals("lineEtc") && requestDTO.getNotificationList().getSubwayInformation().isLineEtc() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLineEtc());
            else if (sub.getTopic().equals("line1") && requestDTO.getNotificationList().getSubwayInformation().isLineEtc() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isLine1());
            else if (sub.getTopic().equals("incheonLine1") && requestDTO.getNotificationList().getSubwayInformation().isIncheonLine1() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isIncheonLine1());
            else if (sub.getTopic().equals("incheonLine2") && requestDTO.getNotificationList().getSubwayInformation().isIncheonLine2() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isIncheonLine2());
            else if (sub.getTopic().equals("westcoastLine") && requestDTO.getNotificationList().getSubwayInformation().isWestcoastLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isWestcoastLine());
            else if (sub.getTopic().equals("geongangLine") && requestDTO.getNotificationList().getSubwayInformation().isGeongangLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isGeongangLine());
            else if (sub.getTopic().equals("airportLine") && requestDTO.getNotificationList().getSubwayInformation().isAirportLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isAirportLine());
            else if (sub.getTopic().equals("bundangLine") && requestDTO.getNotificationList().getSubwayInformation().isBundangLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isBundangLine());
            else if (sub.getTopic().equals("dxLine") && requestDTO.getNotificationList().getSubwayInformation().isDxLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isDxLine());
            else if (sub.getTopic().equals("uiLine") && requestDTO.getNotificationList().getSubwayInformation().isUiLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isUiLine());
            else if (sub.getTopic().equals("yonginLine") && requestDTO.getNotificationList().getSubwayInformation().isYonginLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isYonginLine());
            else if (sub.getTopic().equals("ulrtLine") && requestDTO.getNotificationList().getSubwayInformation().isUlrtLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isUlrtLine());
            else if (sub.getTopic().equals("gimpoLine") && requestDTO.getNotificationList().getSubwayInformation().isGimpoLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isGimpoLine());
            else if (sub.getTopic().equals("sillimLine") && requestDTO.getNotificationList().getSubwayInformation().isSillimLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isSillimLine());
            else if (sub.getTopic().equals("geongchunLine") && requestDTO.getNotificationList().getSubwayInformation().isGeongchunLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isGeongchunLine());
            else if (sub.getTopic().equals("gyoungiLine") && requestDTO.getNotificationList().getSubwayInformation().isGyoungiLine() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getSubwayInformation().isGyoungiLine());
            else if (sub.getTopic().equals("roadAccident") && requestDTO.getNotificationList().getRoadControlInformation().isRoadAccident() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getRoadControlInformation().isRoadAccident());
            else if (sub.getTopic().equals("roadWorks") && requestDTO.getNotificationList().getRoadControlInformation().isRoadWorks() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getRoadControlInformation().isRoadWorks());
            else if (sub.getTopic().equals("rallyEvent") && requestDTO.getNotificationList().getRoadControlInformation().isRallyEvent() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getRoadControlInformation().isRallyEvent());
            else if (sub.getTopic().equals("roadEtc") && requestDTO.getNotificationList().getRoadControlInformation().isRoadEtc() != sub.isSubscribe())
                sub.update(requestDTO.getNotificationList().getRoadControlInformation().isRoadEtc());
        }

        member.update(requestDTO.isNotifyOn());
        memberRepository.save(member);
    }
}
