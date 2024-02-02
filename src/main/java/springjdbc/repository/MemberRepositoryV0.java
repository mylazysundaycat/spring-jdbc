package springjdbc.repository;

//JDBC - DriverManager 사용

import lombok.extern.slf4j.Slf4j;
import springjdbc.connection.DBConnectionUtil;
import springjdbc.domain.Member;

import java.sql.*;
import java.util.NoSuchElementException;

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
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery(); //최초의 커서는 데이터를 가리키고 있지 않다.

            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else{
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close(con, pstmt, rs);
        }

    }

    public void close(Connection con, Statement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
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
