package springjdbc.repository;

//JDBC - DriverManager 사용

import lombok.extern.slf4j.Slf4j;
import springjdbc.connection.DBConnectionUtil;
import springjdbc.domain.Member;

import java.sql.*;

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) values (?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw e;
        } finally {
        if (pstmt!=null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        }
    }

}
