package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.Member;
import javax.sql.DataSource;


public class MemberDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	
	public Member selectOne(int mno) throws Exception{
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			query = "select * from members where mno = ?";
   			pst = conn.prepareStatement(query);
   			pst.setInt(1, mno);
   			rs = pst.executeQuery();
   			rs.next();
   			
   			Member member = new Member();
   			member.setMno(rs.getInt("mno"));
   			member.setMname(rs.getString("mname"));
   			member.setEmail(rs.getString("email"));
   			member.setCre_date(rs.getDate("cre_date"));
   			member.setMod_date(rs.getDate("mod_date"));
   			
   			return member;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(rs !=null) rs.close();} catch(Exception e) {}
   			try {if(pst !=null) pst.close();} catch(Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception{
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {				
			conn = ds.getConnection();
			query = "update members set mname=?, email=?, mod_date=now() where mno=?";
   			pst = conn.prepareStatement(query);
   			pst.setString(1, member.getMname());
   			pst.setString(2, member.getEmail());
   			pst.setInt(3, member.getMno());
   			
   			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
   			try {if(pst !=null) pst.close();} catch(Exception e) {}
		}
	}
	
	public int delete(int mno) throws Exception{
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "delete from members where mno=?";
			pst = conn.prepareStatement(query);
			pst.setInt(1, mno);
			   			
   			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
   			try {if(pst !=null) pst.close();} catch(Exception e) {}
		}
	}
	
	
	public int insert(Member member) throws Exception{
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "insert into members (email, pwd, mname, cre_date, mod_date) values (?, ?, ?, now(), now())";
   			pst = conn.prepareStatement(query);
   			pst.setString(1, member.getEmail());
   			pst.setString(2, member.getPwd());
   			pst.setString(3, member.getMname());
   			
   			return pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
   			try {if(pst !=null) pst.close();} catch(Exception e) {}
		}
	}
	
	
	public List<Member> selectList() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "select * from members order by mno desc";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			ArrayList<Member> members = new ArrayList<Member>();
   			
   			while(rs.next()) {
   				Member m = new Member();
   				m.setMno(rs.getInt("mno"));
   				m.setEmail(rs.getString("email"));
   				m.setMname(rs.getString("mname"));
   				m.setCre_date(rs.getDate("cre_date"));
   				members.add(m);
   			}
   			return members;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
   			try {if(rs !=null) rs.close();} catch(Exception e) {}
   			try {if(st !=null) st.close();} catch(Exception e) {}
   		}
	}
	
	public Member login(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			conn = ds.getConnection();
			query = "select * from web.members where email=? and pwd =?";
			pst = conn.prepareStatement(query);
			pst.setString(1,  member.getEmail());
			pst.setString(2, member.getPwd());
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				Member loginMember = new Member();
				loginMember.setMno(rs.getInt("mno"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setPwd(rs.getString("pwd"));
				loginMember.setMname(rs.getNString("mname"));
				
				return loginMember;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {if(rs !=null) rs.close();} catch(Exception e) {}
   			try {if(pst !=null) pst.close();} catch(Exception e) {}
		}
	}
}