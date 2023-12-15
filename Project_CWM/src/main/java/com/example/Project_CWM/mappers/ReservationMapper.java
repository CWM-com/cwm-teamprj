package com.example.Project_CWM.mappers;

import com.example.Project_CWM.dto.ReservSearchDto;
import com.example.Project_CWM.dto.ReservationOrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Select("SELECT ct.camp_type\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = '예약완료'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)\n" +
            "        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public List<ReservSearchDto> getSearchType(ReservSearchDto reservSearchDto);

    @Select("  SELECT COUNT(ct.camp_type) AS camp_type\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = '예약완료'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)\n" +
            "        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public int getCount(ReservSearchDto reservSearchDto);

    @Select("  SELECT ct.camp_price\n" +
            "FROM campType as ct\n" +
            "WHERE ct.camp_name = #{campName}\n" +
            "  AND ct.camp_type NOT IN (\n" +
            "    SELECT DISTINCT r.camp_type\n" +
            "    FROM reservation as r\n" +
            "    WHERE r.camp_name = #{campName}\n" + "and r.reserv_status = '예약완료'" +
            "      AND (\n" +
            "        (#{checkin} BETWEEN r.reserv_Check_in AND r.reserv_Check_out - INTERVAL 1 DAY)\n" +
            "        OR (#{checkout} BETWEEN r.reserv_Check_in + INTERVAL 1 DAY AND r.reserv_Check_out)\n" +
            "        OR (r.reserv_Check_in <= #{checkin} AND r.reserv_Check_out > #{checkout})\n" +
            "      )\n" +
            "  );")
    public List<ReservSearchDto> getPrice(ReservSearchDto reservSearchDto);

    @Insert("insert into reservation values(null,#{reservName},#{reservTel},#{campName},#{campType},#{checkin},#{checkdays},#{checkout},#{adultNum},#{teenNum},#{cildNum},#{reservStatus},#{memIdx},#{impUid})")
    public void getReservationList(ReservationOrderDto reservationOrderDto);
}
