package kim.jaehoon.studentable.signo.util;

import kim.jaehoon.studentable.signo.exception.RegionNotFoundException;
import kr.go.neis.api.School;

public class RegionConverter {

    public static School.Region toEnum(String schoolFullName) {

        if (schoolFullName.contains("서울")) return School.Region.SEOUL;
        if (schoolFullName.contains("인천")) return School.Region.INCHEON;
        if (schoolFullName.contains("부산")) return School.Region.BUSAN;
        if (schoolFullName.contains("광주")) return School.Region.GWANGJU;
        if (schoolFullName.contains("대전")) return School.Region.DAEJEON;
        if (schoolFullName.contains("대구")) return School.Region.DAEGU;
        if (schoolFullName.contains("세종")) return School.Region.SEJONG;
        if (schoolFullName.contains("울산")) return School.Region.ULSAN;
        if (schoolFullName.contains("경기")) return School.Region.GYEONGGI;
        if (schoolFullName.contains("강원")) return School.Region.KANGWON;
        if (schoolFullName.contains("충청북도")) return School.Region.CHUNGBUK;
        if (schoolFullName.contains("충청남도")) return School.Region.CHUNGNAM;
        if (schoolFullName.contains("경상북도")) return School.Region.GYEONGBUK;
        if (schoolFullName.contains("경상남도")) return School.Region.GYEONGNAM;
        if (schoolFullName.contains("전라북도")) return School.Region.JEONBUK;
        if (schoolFullName.contains("전라남도")) return School.Region.JEONNAM;
        if (schoolFullName.contains("제주")) return School.Region.JEJU;

        throw new RegionNotFoundException();
    }
}
