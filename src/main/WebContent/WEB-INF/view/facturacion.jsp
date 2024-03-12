<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Facturacion</h1>
	<button id="mostrar-cliente">Agregar Cliente</button>
	<button id="mostrar-pedido">Agregar Pedido</button>
	<button id="mostrar-formapago">Agregar Forma de pago</button>
	<button id="mostrar-empresaProducto">Agregar Empresa Producto</button>
	<button id="mostrar-producto">Agregar Producto</button>
	
	
	<textarea >${empresa_productosAgregados}</textarea>
	<textarea >${productosAgregados}</textarea>
	
	<hr/>
	
	<div>
		<dialog id="alert-dialog-clientes">
			<form action="findOneCliente" id="formCliente" name="formCliente" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>Id Cliente</th>
							<th>Cedula</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Direccion</th>
							<th>Telefono</th>
							<th>Correo</th>
							<th>Genero</th>
							<th>Fecha de Nacimiento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${clientes}">
							<tr>
							<td>
								<button><a href="${pageContext.request.contextPath}/facturacion/findOneCliente?idCliente=${item.idCliente}">Agregar</a></button>							
							</td>
					        <td>${item.idCliente}</td>
					        <td>${item.cedula}</td>
					        <td>${item.nombre}</td>
					        <td>${item.apellido}</td>
					        <td>${item.direccion}</td>
					        <td>${item.telefono}</td>
					        <td>${item.correo}</td>
					        <td>${item.genero}</td>
					        <td>${item.fechadenacimiento}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>
	
	<div>
		<dialog id="alert-dialog-pedidos">
			<form action="findOnePedido" id="formPedido" name="formPedido" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>ID Pedido</th>
							<th>Numero de Pedido</th>
							<th>Fecha del pedido</th>
							<th>Confirmacion de Pedido</th>
							<th>Cliente</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${pedidos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOnePedido?idPedido=${item.idPedido}">Agregar</a></button>
								</td>
								<td>${item.idPedido}</td>
								<td>${item.numPedido}</td>
								<td>${item.fechapedido}</td>
								<td>${item.confirmacionPedido}</td>
								<td>${item.cliente.cedula} ${item.cliente.nombre}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>
	
		
	<div>
		<dialog id="alert-dialog-formapagos">
			<form action="findOneFormaPago" id="formFormaPago" name="formFormaPago" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>ID Forma de Pago</th>
							<th>Forma Pago</th>
							<th>Descripcion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${formapagos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOneFormaPago?idFormaPago=${item.idFormaPago}">Agregar</a></button>
								</td>
								<td>${item.idFormaPago}</td>
								<td>${item.formaspago}</td>
								<td>${item.descripcion}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>
	
	<div>
		<dialog id="alert-dialog-productos">
		
			<form action="findOneProducto" id="formProducto" name="formProducto" method="get" >
				<table>
					<thead>
						<tr>
							<th>idProducto</th>
							<th>numeroProducto</th>
							<th>descripcion</th>
							<th>precioProducto</th>
							<th>stock</th>
							<th>ImgProducto</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${productos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOneProducto?idProducto=${item.idProducto}">Agregar</a></button>
								</td>
								<td>${item.idProducto}</td>
								<td>${item.numeroProducto}</td>
								<td>${item.descripcion}</td>
								<td>${item.precioProducto}</td>
								<td>${item.stock}</td>
								<td>porducto</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form> 
			
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>

	<div>
		<dialog id="alert-dialog-EmpresaProductos">
			<form action="findOneEmpresaProducto" id="formEmpresaProducto" name="formEmpresaProducto" method="get">
				<table>
					<thead>
						<tr>
							<th>ID Empresa Producto</th>
							<th>Producto</th>
							<th>Descripcion</th>
							<th>Empresa</th>
							<th>Producto</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${empresa_productos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOneEmpresaProducto?idempresa_producto=${item.idempresa_producto}">Agregar</a></button>
								</td>
								<td>${item.idempresa_producto}</td>
								<td>${item.producto}</td>
								<td>${item.descripcion}</td>
								<td>${item.idempresa.nombreempresa}</td>
								<td>${item.idproducto.numeroProducto}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>	

	<form action="addDetalle" id="formDetalles" name="formDetalles"  method="post">
		Numero de Factura
		<input type="text" id="numFactura" name="numFactura" value="${numFactura}" disabled />	
		Fecha
		<input type="date" id="fechaActual" name="fechaActual" value="${fn:substring(fechaActual,0,10)}"  disabled />		
		TOTAL NETO
		<input type="number"step="any" id="totalNeto" name="totalNeto" value="${totalNeto}" placeholder="00.00" disabled />
		IVA
		<input type="number" step="any" id="iva" name="iva" value="${iva}" placeholder="00.00" disabled />
		TOTAL
		<input type="number" step="any" id="total" name="total" value="${total}" placeholder="00.00" disabled />
		<button type="submit" onclick="${pageContext.request.contextPath}/facturacion/addDetalle?list=${list}">COMPRAR</button>	
		<br/><br/>
		<hr/>
		
		<h3> Cliente </h3>		       					
		Cédula
		<input type="hidden" id="idCliente" name="idCliente" value="${cliente.idCliente}" />
		<input type="text" id="cedula" name="cedula" value="${cliente.cedula}" disabled />				
		Nombre
		<input type="text" id="nombre" name="nombre" value="${cliente.nombre}" disabled />				
		Apellido
		<input type="text" id="apellido" name="apellido" value="${cliente.apellido}" disabled />				
		Direccion
		<input type="text" id="direccion" name="direccion" value="${cliente.direccion}" disabled />
		Telefono
		<input type="text" id="telefono" name="telefono" value="${cliente.telefono}" disabled />
		Correo
		<input type="email" id="correo" name="correo" value="${cliente.correo}" disabled />
	</form>
	<hr/>
	
<form action="addDetalleTemporal"  id="formDetallesTemporal" name="formDetallesTemporal" method="get">
	<h3> <label hidden="true">Productos</label></h3>
 
    <input type="hidden" id="opcionDetalle" name="opcionDetalle" value="1" />

    <br/>


    <input type="hidden" id="idFDList" name="idFDList" value="" />

    <br/><br/>

    <div>
        <div style="float:left">
            <img style="display: none;" id="ImgProducto" name="ImgProducto" width="250" height="250" src="" />
        </div>

        <div>
            <label hidden="true">PRODUCTO</label>
            <input style="display: none;" type="text" id="producto" name="producto" value="" disabled />

            <br/>

            <label hidden="true">DESCRIPCIÓN</label>
            <input style="display: none;" type="text" id="descripcion" name="descripcion" value="" disabled />

            <br/>

            <label hidden="true">STOCK</label>
            <input style="display: none;" type="number" id="numStock" name="numStock" value="" disabled />

            <br/>

            <label hidden="true">CANTIDAD</label>
            <input style="display: none;" type="number" id="cantidad" name="cantidad" value="" />

            <br/>

            <button style="display: none;" id="btnGuardar" name="btnGuardar" type="button" onclick="document.getElementById('opcionDetalle').value='1';
                    document.getElementById('producto').style.display='none';
                    document.getElementById('descripcion').style.display='none';
                    document.getElementById('ImgProducto').style.display='none';
                    document.getElementById('cantidad').style.display='none';
                    document.getElementById('numStock').style.display='none';
                    document.getElementById('btnGuardar').style.display='none';
                    document.getElementById('formDetallesTemporal').submit();">Guardar</button>
        </div>
    </div>

    <br/><br/>
    <br/><br/>

    <div>
        <h3>Agregar Detalles</h3>

        <c:forEach var="item" items="${list}">
            <input type="hidden" value="${item.idFacturaDetalle}">
            Cantidad
            <input type="number" value="${item.cantidad}" disabled>
            Descripción
            <input type="text" value="${item.empresaProducto.descripcion}" disabled>
            Precio
            <input type="number" step="any" value="${item.empresaProducto.idproducto.precioProducto}" disabled>
            Subtotal
            <input type="number" step="any" value="${item.subTotal}" disabled>
            Stock
            <input type="number" step="any" value="${item.empresaProducto.idproducto.stock}" disabled>

            <button type="button" onclick="document.getElementById('opcionDetalle').value='1';
                    document.getElementById('idFDList').value=${item.idFacturaDetalle};
                    document.getElementById('producto').value='${item.empresaProducto.producto}';
                    document.getElementById('descripcion').value='${item.empresaProducto.descripcion}';
                    document.getElementById('cantidad').value=${item.cantidad};
                    document.getElementById('stock').value=${item.empresaProducto.idproducto.stock};
                    document.getElementById('portada').src='${pageContext.request.contextPath}/resources/img/${item.empresaProducto.idproducto.imagenProducto}';
                    document.getElementById('producto').style.display='block';
                    document.getElementById('descripcion').style.display='block';
                    document.getElementById('portada').style.display='block';
                    document.getElementById('cantidad').style.display='block';
                    document.getElementById('numStock').style.display='block';
                    document.getElementById('btnGuardar').style.display='block';">Editar</button>

            <button type="button" onclick="document.getElementById('opcionDetalle').value='2';
                    document.getElementById('idFDList').value=${item.idFacturaDetalle};
                    document.getElementById('formDetalleTemporal').submit();">Eliminar</button>

            <br/>
        </c:forEach>
    </div>

</form>

	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
	<script>
		
	const mostrarCliente = document.querySelector("#mostrar-cliente"); 
	mostrarCliente.addEventListener("click", function () {
		const alertDialogClientes = document.querySelector("#alert-dialog-clientes");
		alertDialogClientes.showModal();
	});
	
	const mostrarPedido = document.querySelector("#mostrar-pedido"); 
	mostrarPedido.addEventListener("click", function () {
		const alertDialogPedidos = document.querySelector("#alert-dialog-pedidos");
		alertDialogPedidos.showModal();
	});
	
	const mostrarFormaPago = document.querySelector("#mostrar-formapago"); 
	mostrarFormaPago.addEventListener("click", function () {
		const alertDialogFormapagos = document.querySelector("#alert-dialog-formapagos");
		alertDialogFormapagos.showModal();
	});
	
	const mostrarEmpresaProducto = document.querySelector("#mostrar-empresaProducto"); 
	mostrarEmpresaProducto.addEventListener("click", function () {
		const alertDialogEmpresaProductos = document.querySelector("#alert-dialog-EmpresaProductos");
		alertDialogEmpresaProductos.showModal();
	});
	
	const mostrarProducto = document.querySelector("#mostrar-producto"); 
	mostrarProducto.addEventListener("click", function () {
		const alertDialogProductos = document.querySelector("#alert-dialog-productos");
		alertDialogProductos.showModal();
	});
	
	</script>
	
</body>
</html>