<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aplicacion de Gestion de Ingredientes</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	 <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
                    <div>
                        <a href="#" class="navbar-brand"> Ingredientes Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Ingredientes</a></li>
                    </ul>
                </nav>
            </header>
            <br>
                        <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${ingrediente != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${ingrediente == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${ingrediente != null}">
                                    Edit Ingrediente
                                </c:if>
                                <c:if test="${ingrediente == null}">
                                    Add New Ingrediente
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${ingrediente != null}">
                            <input type="hidden" name="id" value="<c:out value='${ingrediente.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Ingrediente Nombre</label> <input type="text" value="<c:out value='${ingrediente.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Ingrediente Cantidad</label> <input type="text" value="<c:out value='${ingrediente.cantidad}' />" class="form-control" name="cantidad">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Ingrediente Precio</label> <input type="text" value="<c:out value='${ingrediente.precio}' />" class="form-control" name="precio">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>   
</body>
</html>