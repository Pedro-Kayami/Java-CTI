<html>

<%@page language="Java" import="java.sql.*" %>    
<%@page language="Java" import="javax.swing.*" %>
<%@page language="Java" import="database.BancoSql" %>
<%@page language="Java" import="database.Jogos" %>
<%@page language="Java" import="java.util.*" %>


<body background="unesp.jpg">
<head>
<link rel="icon" href="unesp.png" type="image/jpg" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:: Alterar / Excluir::</title>
</head>

<center>
<form action="games.jsp" method="get">
<font color="#000">
<b><n> -----------------ALTERA/EXCLUI-----------------------------------------------------</n></b>
<br>
<br>
			<font color="#FF0000" style='font-weight:bold;'>
			<%

   			String zid = request.getParameter("id");
   			String zmodelo= request.getParameter("nomeJogo");
			String zmarca = request.getParameter("estudio");
			String zraio = request.getParameter("categoria");
			String zpreco = request.getParameter("preco");
			String znovafoto =request.getParameter("novafoto");
			String caminhofoto="c://xampp//tomcat//webapps//Jogos//foto//";
			byte[] xfotorig=null;
			
			//JOptionPane.showMessageDialog(null,"foto escolhida"+znovafoto);
			
			BancoSql.conectar();
	        ArrayList vetor=new ArrayList();
	        vetor=BancoSql.pegaBanco();
			long lnumero=Long.parseLong(zid);
			
			int jj=vetor.indexOf(lnumero); 
		    System.out.println("posicao do vetor="+jj);
		    String xid=""+vetor.get(jj); jj++;
		    String xmod=""+vetor.get(jj); jj++;
			String xmar=""+vetor.get(jj); jj++;
			String xpreco=""+vetor.get(jj); jj++;
			String xraio=""+vetor.get(jj); jj++;
			xfotorig=(byte [])vetor.get(jj);
			
				
			if(znovafoto.length()!=0)
			{
			   caminhofoto=caminhofoto+znovafoto;
			   //JOptionPane.showMessageDialog(null,"ok"+nome+"-"+turma+"-"+matricula+"-"+caminhofoto);
			   xfotorig=BancoSql.imageToByte(caminhofoto);
			}
			
			String altexc = request.getParameter("altexc");
   			//JOptionPane.showMessageDialog(null,""+altexc);
   			Jogos zzz=new Jogos();
  	        zzz.setId(Long.parseLong(zid));
  	        zzz.setNome(zmodelo);
			zzz.setEstudio(zmarca);
			zzz.setCategoria(zraio);
			double vpreco=Double.parseDouble(zpreco);
			zzz.setPreco(vpreco);
			zzz.setFoto(xfotorig);

  	 
        	if(altexc.equals("excluir")){
			   //BancoSql.setMat(matricula); nao tem
			 
			   
			   BancoSql.remove(zzz);
			
			   out.println("\n<h1> Dados " + zmodelo + " Excluidos com sucesso !</h1>");
   			
			}else
			{
			    BancoSql.altera(zzz);
			    out.println("\n <h1>Dados " + zmodelo + " Alterados com sucesso !</h1>");
			}
      		 BancoSql.desconectar();
			%>
 <input type="submit" name="teste" value="Continuar Alterando ou Excluindo >" align="top" /><br>

<br>
<br>

</form>

</center>
</body>
</html>
