package com.nsm.mymvc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisServlet
 */
@WebServlet("/DisServlet")
public class DisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisServlet() {
    	List<String> packageClassNames = getPackageClassNames("com.nsm.sort");
    	//packageClassNames.forEach(System.out::println);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String contextPath = request.getContextPath();
		request.getRequestDispatcher("ffff.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private List<String> getPackageClassNames(String packageName){
		
		URL url = this.getClass().getResource("./");
		String path = url.getFile()+packageName.replaceAll("\\.", "/");
		System.out.println(path);
		File f = new File("C:\\work\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\HelloWorld\\WEB-INF\\classes\\com\\nsm\\sort");
		
//		FileFilter classFileFileter = (file)->{
//			if(file.isDirectory()){
//				return false;
//			}
//			String fileName = file.getName();
//			if(fileName.lastIndexOf('.')==-1){
//				return false;
//			}
//			String suffix = fileName.substring(fileName.lastIndexOf('.')+1, fileName.length());
//			if(suffix.equals("class")){
//				return true;
//			}else{
//				return false;
//			}
//		};
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			System.out.println(file.getName());
		}
//		if(listFiles.length==0){
//			return null;
//		}
//		List<String> classNames = new ArrayList<>();
//		for (File classFile : listFiles) {
//			classNames.add(packageName+'.'+classFile.getName().substring(0, classFile.getName().lastIndexOf(".")));
//		}
		return null;
	}
}
