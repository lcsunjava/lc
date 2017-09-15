package cn.tedu.uitl;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSession getSqlSession(){
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream instream = 
			MyBatisUtil.class.getClassLoader().
			getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = builder.build(instream);
		SqlSession session = factory.openSession();
		return session;
	}
}
