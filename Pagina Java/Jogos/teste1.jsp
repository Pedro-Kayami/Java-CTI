<html>
<body>
       <%-- coment�rio em JSP aqui: nossa primeira p�gina JSP --%>
       <%
         String mensagem = "Bem vindo ao sistema de agenda do CASTRO";
         String desenvolvido = "Desenvolvido por (EU)";
         out.println(mensagem);
       %> 
        <br />
       <%=desenvolvido         %> 
         <br />
       <%@page import="java.sql.*" %>    
       <%@page import="javax.swing.*" %>
       <%
         JOptionPane.showMessageDialog(null,"vai garoto");
		  out.println("Tudo foi executado!");
		  %>
         <br> 
         <br>
         <%
         int j=0;
         for(j=0;j<10;j++) out.println("contando="+j+"<br>");
         %>
</body>
</html>
