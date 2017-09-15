package cn.tedu.test;

import org.apache.ibatis.session.SqlSession;
import cn.tedu.mapper.EmpMapper;
import cn.tedu.pojo.Emp;
import cn.tedu.uitl.MyBatisUtil;

public class TestPagination {
	public static void main(String[] args) {
		//测试Mybatis一级缓存
		SqlSession session = MyBatisUtil.getSqlSession();
		EmpMapper em =  session.getMapper(EmpMapper.class);
		Emp emp = em.getEmpByNo(7369);
		System.out.println(emp);
		
		em.deleteEmpByNo(9999);
		session.commit();
		
		Emp emp1 = em.getEmpByNo(7369);
		System.out.println(emp);
		
		System.out.println(emp == emp1);
		
		//测试Mybatis二级缓存
		/*SqlSession session1 = MyBatisUtil.getSqlSession();
		SqlSession session2 = MyBatisUtil.getSqlSession();
		
		EmpMapper em01 =  session1.getMapper(EmpMapper.class);
		Emp emp1 = em01.getEmpByNo(7369);
		System.out.println(emp1);
		em01.deleteEmpByNo(9999);

		session1.close();
		
		EmpMapper em02 =  session2.getMapper(EmpMapper.class);
		Emp emp2 = em02.getEmpByNo(7369);
		System.out.println(emp2);
		session2.close();*/
		
		//测试Mybatis调用PL/SQL编写好的存储过程
		/*Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("ename", "SMITH");
        parameterMap.put("mname", "");
        String statement = "cn.tedu.mapper.EmpMapper.getManager";
        session.selectOne(statement, parameterMap);
        String mname = parameterMap.get("mname");
        System.out.println(mname);*/
        
		/*Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pagenow", 1);
        parameterMap.put("pagesizes", 5);
        parameterMap.put("countsizes", -1);
        parameterMap.put("countpages", -1);
        parameterMap.put("cur", new ArrayList<Map<String, Object>>());
        
        String statement = "cn.tedu.mapper.EmpMapper.findPagination";
        session.selectOne(statement, parameterMap);
        System.out.println(parameterMap.get("countsizes"));
        System.out.println(parameterMap.get("countpages"));
        System.out.println(parameterMap.get("cur"));
        
        List<Map<String, Emp>> emps = (List<Map<String, Emp>>) parameterMap.get("cur");
        for(Map<String, Emp> m : emps){
        	for(Map.Entry<String, Emp> en : m.entrySet()){
        		System.out.print(en.getValue()+" ");
        	}
        	System.out.println();
        }
        */
        session.close();
	}

}
