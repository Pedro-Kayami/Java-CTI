<html>

<body background="download.jfif">
    <%@page language="Java" import="java.sql.*" %>
        <%@page language="Java" import="javax.swing.*" %>
            <%@page language="Java" import="database.BancoSql" %>
                <%@page language="Java" import="database.Jogos" %>
                    <%@page language="Java" import="java.util.*" %>
                        <%@page language="Java" import="java.util.Base64" %>

                            <%-- comentario em JSP aqui: nossa primeira pagina JSP --%>

                                <% 
                                String znomeJogo=request.getParameter("nomeJogo"); 
                                String zEstudio=request.getParameter("estudio"); 
                                String zCategoria=request.getParameter("categoria"); 
                                String zpreco=request.getParameter("preco"); 
                                String nomefoto=request.getParameter("foto");
                                String caminhofoto="c://xampp//tomcat//webapps//Jogos//foto//" +nomefoto; 
                                byte[] bytefoto=BancoSql.imageToByte(caminhofoto); 
                                Jogos rrr=new Jogos();
                                rrr.setNome(znomeJogo); 
                                rrr.setEstudio(zEstudio); 
                                rrr.setCategoria(zCategoria); 
                                double vpreco=Double.parseDouble(zpreco); 
                                rrr.setPreco(vpreco);
                                rrr.setFoto(bytefoto);
                                BancoSql.conectar(); 
                                BancoSql.adiciona(rrr); 
                                BancoSql.desconectar(); %>


                                    <head>
                                        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                                    </head>

                                    <form action="games.jsp" method="get">

                                        <font color="#000">
                                            <b>
                                                <n> -- Cadastrar novos
                                                    ------------------------------------------------------</n>
                                            </b>
                                            <br>
                                            <br>
                                            <br>
                                            <font color="#FF0000" style='font-weight:bold;'>
                                                Dados do <% 
                                                String saida=""; 
                                                String textoSerializado="";
                                                textoSerializado=Base64.getEncoder().encodeToString(bytefoto);
                                                saida="data:image/jpg;base64," + textoSerializado;
                                                out.println("|"+znomeJogo+"|"+zEstudio+"|<br> <br>" );
                                                out.println("<figure> <img src='"+saida+"' width='100' height='100' ' /> </figure> <br>");
 %> adicionado com sucesso! 
 <input 

type="submit" name="teste" value="Continuar Cadastrando >" align="top" /><br>


<br>
<br>

<br>
<br>
</form>

</center>

</body>
</html>