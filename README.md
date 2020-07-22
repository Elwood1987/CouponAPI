CouponAPI
==========
Introducción
--------------------
Coupon API es uns solución desarrollada como prueba técnica implementada por Cristian E Castro Lara. El API cuenta con la capacidad /coupon/ en donde se envia una lista de identificadores de items y un valor de un cupón y retorna los items que podria comprar un usuario con el precio establecido en el cupón. 

La implementación del proyecto se hizo en Intellij haciendo uso de Springboot y maven para el manejo de dependencias. También se hizo uso de rest template para el consumo del API de consulta de precios de los items. Adicionalmente, se incluyeron un ciclo de pruebas unitarias que fueron verificadas mediante Jacoco para obtener un informe de Code Coverage del desarrollo.

Se plantearon tres entidades a nivel de modelo para dar respuesta al requerimiento y pensando en el escalamiento del mismo. Se implementó la entidad Coupon como entidad principal, que contiene una lista de items. CouponRequest y CouponResponse, obedecen a la necesidad de distinguir el request (el ingreso de items que un usuario quiere comprar y el monto del cupón que puede comprar) y el response (items que el usuario puede comprar y el total maximo que podria comprar teniendo en cuenta el monto del cupón). 

También se implemento una clase Util, en donde se realiza el calculo de los items que el usuario puede comprar, la carga de un archivo de propiedades y el ordenamiento de un Map. El archivo de propiedades contiene la ruta donde se consumira el servicio para la consulta del valor de los items. 

Instalación
--------------------
1. Clonar el repositorio en una carpeta local
  + git clone https://github.com/Elwood1987/CouponAPI.git
  + cd CouponAPI
  
2. Importar el proyecto en IDE (Eclipse, Intellij)

3. Configurar el archivo application.properties en caso de querer modificar el puerto.

4. El archivo config.properties contiene la URL del servicio de consulta de precios de item.

5. Para ejecución de los test, ejecutar el comando mvn test jacoco:report.

Manos a la obra
--------------------
En Postman, es posible validar el funcionamiento del proyecto haciendo uso del siguiente payload:
{
"item_ids": [
	"MCO498011211",
	"MCO458208093",
	"MCO457953350",
	"MCO458209042",
	"MCO558174274",
	"MCO451945333",
	"MCO451945279",
	"MCO451945403",
	"MCO451945560",
	"MCO451945848",
	"MCO506099436",
	"MCO506099509",
	"MCO506099653",
	"MCO506099631",
	"MCO506099644",
	"MCO506099618",
	"MCO515806038",
	"MCO515806051"],
"amount":500000
}
