package cn.tedu.mapper;

import java.util.List;

import cn.tedu.pojo.Emp;

public interface EmpMapper {
	public List<Emp> findPagination(Integer pagenow, Integer pagesizes);
	public String getManager(String ename);
	public Emp getEmpByNo(Integer empno);
	public void deleteEmpByNo(Integer empno);
}
