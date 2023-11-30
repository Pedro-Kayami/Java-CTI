<html>   
   <%@page import="java.sql.*" %>    
   <%@page import="javax.swing.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.io.*" %>
   <%@page import="net.sf.jasperreports.engine.*"%>
   <%@page import="net.sf.jasperreports.extensions.*"%>
   <%@page import="net.sf.jasperreports.view.JasperViewer"%>
   <%@page language="Java" import="database.BancoSql" %>

<body>
       <%-- comentário em JSP aqui: nossa primeira página JSP --%>
       
       <%
         String mensagem = "Relatorio web JSP";
         out.println(mensagem); %> 
         <br />
       <%
        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;
        String fsql="";
    
	     try
    	{
    		String url="jdbc:sqlite:\\xampp\\tomcat\\webapps\\Jogos\\banco\\Jogos.db";
            String drive="org.sqlite.JDBC";//drive de conexao
			Class.forName(drive);
    	    con=DriverManager.getConnection(url);
  
    	    System.out.println("conectou no banco ");
    	    
    	   
     		fsql="Select * from tabjogos";

    	    pstmt = con.prepareStatement(fsql);
            rs = pstmt.executeQuery();
			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
			JasperReport report=null;
			
     		report = JasperCompileManager.compileReport("C:\\xampp\\tomcat\\webapps\\Jogos\\RealJogos.jrxml");  
     		 
     		    
            JasperPrint print = JasperFillManager.fillReport(report, null, jrRS);  
            JasperViewer viewer = new JasperViewer(print, false);  
            viewer.setVisible(true);  

            //fechando o banco
    	      System.out.println("foi acabou");
    		pstmt.close();
    		con.close();
    		
    		
         }//try
    	catch(Exception e)
    	{
    		 System.out.println("ERRO relatorio="+e);
    		
    	}//catch
     
        %>

   <script language='javascript'>
  alert('FIM DO RELATORIO');
  location.href='http://localhost:8080/Jogos/index.html'
  </script>
</body>
</html>
