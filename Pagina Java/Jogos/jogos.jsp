<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>

<%@page language="Java" import="java.sql.*" %>
	<%@page language="Java" import="javax.swing.*" %>
		<%@page language="Java" import="database.BancoSql" %>
			<%@page language="Java" import="database.Jogos" %>
				<%@page language="Java" import="java.util.*" %>
					<%@page language="Java" import="java.util.Base64" %>

<head>
	<title>CADASTRO DE Jogos CRUD</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="generator" content="Geany 1.38" />
</head>

<body>
	<h1> CRUD PEDRO KAYAMI 73C NUMERO: 11 </h1>
	<h3> Jogos Cadastradas</h3>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Estudio</td>
			<td>Categoria</td>
			<td>Preco</td>
			<td>Foto</td>
			<td>Editar</td>
		</tr>
		
		<% try { BancoSql.conectar(); 
			ArrayList vetor=new ArrayList(); 
			vetor=BancoSql.pegaBanco(); 
			String zid,znome,zestudio, zcategoria, zpreco, saida; 
			String textoSerializado; 
			byte[] xfot; 
			
			if (!vetor.isEmpty()) {
				
			for(int j=0; j < vetor.size(); j++) 
			{
				 zid="" + vetor.get(j); j++; 
				 znome="" + vetor.get(j); j++;
				 zestudio="" + vetor.get(j); j++;
				 zcategoria="" + vetor.get(j); 
				 zpreco="" + vetor.get(j); j++;
				 xfot=(byte[]) vetor.get(j);
				 
			 saida="" ; textoSerializado="" ;
			 if (xfot !=null) 
			 {
			   textoSerializado=Base64.getEncoder().encodeToString(xfot); 
			  } 
			  saida="data:image/png;base64,"+textoSerializado; %>
			<tr>
				<td>
					<%=zid %>
				</td>
				<td>
					<%=znome %>
				</td>
				<td>
					<%=zestudio %>
				</td>
				<td>
					<%=zcategoria %>
				</td>
				<td>
					<%=zpreco %>
				</td>
				<td>
					<% out.println("<figure><img src='" + saida + "' alt='" + znome + "' width='40' height='40' /></figure><br>"); %>

				</td>
				<td>
					<% out.println("<a href='editar.jsp?id=" + zid + "'>Link para editar</a>"); %>
				</td>
			</tr>
			<% } 
			} else { out.println("<br>Banco de dados vazio!!!!!");
				}
				BancoSql.desconectar();
				} catch (Exception e) {
				out.println("ERRO WEB =" + e);
				}
				%>
	</table>
	<table border="2">
		<tr>
			<td><a href="adiciona.html">Novo registro</a></td>
			<td><a href="index.html">Inicio trabalho</a></td>
		</tr>
	</table>
</body>

</html>
