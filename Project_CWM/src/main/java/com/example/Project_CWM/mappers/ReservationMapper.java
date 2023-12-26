package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.ReservSearchDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReservationMapper {

    @Select("SELECT ct.camp_type\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = 'complete'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.check_in AND r.check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.check_in + INTERVAL 1 DAY AND r.check_out)\n" +
            "        OR (r.check_in <= #{checkin} AND r.check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public List<ReservSearchDto> getSearchType(ReservSearchDto reservSearchDto);

    @Select("  SELECT COUNT(ct.camp_type) AS camp_type\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = 'complete'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.check_in AND r.check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.check_in + INTERVAL 1 DAY AND r.check_out)\n" +
            "        OR (r.check_in <= #{checkin} AND r.check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public int getCount(ReservSearchDto reservSearchDto);

    @Select("  SELECT ct.camp_price\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = 'complete'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.check_in AND r.check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.check_in + INTERVAL 1 DAY AND r.check_out)\n" +
            "        OR (r.check_in <= #{checkin} AND r.check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public List<ReservSearchDto> getPrice(ReservSearchDto reservSearchDto);

    @Insert("insert into reservation values(null,#{reservName},#{reservTel},#{campName},#{campType},#{checkin},#{checkdays},#{checkout},#{adultNum},#{teenNum},#{cildNum},#{reservStatus},#{memIdx},#{impUid},#{merchantUid},now())")
    public void getReservationList(ReservationOrderDto reservationOrderDto);

    // searchQuery에 따른 출력
    @Select("select r.*, p.place_code from reservation as r inner join placeinfo as p on(r.camp_name = p.place_name) where ${searchQuery} mem_idx = #{memIdx} order by reserv_idx desc limit #{startNum}, #{offset}")
    public List<ReservationOrderDto> OrderList(Map<String,Object> map);

    // searchQuery에 따른 카운트
    @Select("select count(*) from reservation where ${searchQuery} mem_idx = #{memIdx}")
    public int ReservCount(String memIdx,String searchQuery);


    // 전체 / 완료 / 취소 별로 출력 끝
    @Update("update reservation set reserv_status = 'cancel' where imp_uid = #{impUid} ")
    public void updateReserInfo(String impUid);

    // myPage 메인 최근 캠핑장
    @Select("select camp_name, camp_type, check_in from reservation where mem_idx = #{idx} order by reserv_idx desc limit 0,1; ")
    public List<ReservationOrderDto> recentCamp(int idx);

    //review에 사용하는 것
    @Select("select count(reserv_status) from reservation where mem_idx = #{memIdx} and reserv_status = 'complete'")
    public int checkReview(int idx);

    @Select("SELECT camp_name FROM reservation WHERE reserv_status = 'complete'")
    List<String> getAllCampNames();



}
