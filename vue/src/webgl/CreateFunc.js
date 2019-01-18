import { params } from "@/webgl/AllParams";
import { getPlanets, getBases, onPlanet } from "@/services/planets";
import { getShips, updateShip } from "@/services/ships";
import { moveShip } from "@/services/websocket";
import store from '../vuex/index'
import { GET_SHIP } from "@/vuex/actions/user";
import { component } from "@/main";


class Ship{
  constructor(pX, pY, pZ, rX, rY, rZ, s,){
    this.positionX = pX;
    this.positionY = pY;
    this.positionZ = pZ;
    this.rotationX = rX;
    this.rotationY = rY;
    this.rotationZ = rZ;
    this.speed = s;
  }
}

export function startWebGL() {
    params.dataForSystem.pointReady=0;
    params.dataAboutPlanet.meshs=[];
    params.ships.arrShips = [];

    var canvas = document.getElementById('canvas');

    canvas.setAttribute('width', params.dataForSystem.width);
    canvas.setAttribute('height', params.dataForSystem.height);


    createRenderer();
    createScene();
    createCamera(65, 0.1, 170000, -1000, 1000, 1000);
    createLight(0xffffff);
    params.dataForSystem.scene.add(params.dataForSystem.light);



    getPlanets(1)
      .then(()=> {
        // Создание планет

        fillLengthPlanetCenterPlanetAndAngle();

        for (var i = 0; i < params.dataAboutPlanet.planets.length; i++) {

          createPlanet(i, i);

        }
        createAllBase()

        getShips()
          .then(() => {
            params.ships.ships.forEach((ship) => {
              createObjectShip('shuttle', ship, false)
            })
          })


        // Блок кода, который понадобится в дальнейшем для определения направления мыши
        params.objectHover.raycaster = new THREE.Raycaster();
        document.addEventListener('mousemove', onDocumentMouseMove, false);
        window.addEventListener('resize', onWindowResize, false);


        var id;
        function look() {





          // function  lookQuickly() {

          if (params.dataForSystem.scene.background == undefined) {
            params.dataForSystem.scene.background = params.dataForSystem.background;
          }

          if (params.dataForSystem.pointReady == params.dataAboutPlanet.planets.length) {

            updataPositionPlanet();


            //Конечно может возникнуть ситуация, в которой мы решили навести мышку на центральный объект и перезагрузить стр (через f5), тогла INTERSECTED будет равен нашему объекту и не увеличется(из-за того что INTERSECTED == intersects[0].object), хотя мы мышка указывает на этот обьект, если мы уберем указатель с этого объекта он не изменится в размерах тк count = 1
            // Здесь определяем куда была наведена наша мышка и в соответствии с эитм осуществяем какие-то действия
            params.objectHover.raycaster.setFromCamera(params.objectHover.mouse, params.dataForSystem.camera);
            // params.objectHover.intersects = params.objectHover.raycaster.intersectObjects(params.dataForSystem.scene.children);
            // При первой прогрузке log выдает результат равный кол. объектов на сцене, поэтому во 2 if нужно поставить условие ...<countPlanet (тк это все равно не возможно)
            // console.log(params.objectHover.intersects.length);

            params.objectHover.intersects = params.objectHover.raycaster.intersectObjects(params.dataForSystem.scene.children, true);



            if (params.objectHover.intersects.length > 0) {


                // if(params.objectHover.intersects[0].children != 6){
                //   alert('HELLO')
                //   params.objectHover.intersects = null;
                // }

              if(params.objectHover.intersects[0].object.name != 'base') {

                if(typeof params.objectHover.intersects[0].object.name == 'string'){
                if(params.objectHover.intersects[0].object.name.indexOf("ship") != -1) {
                  if (id != params.objectHover.intersects[0].object.name.substring(params.objectHover.intersects[0].object.name.indexOf("_") + 1)) {
                    id = params.objectHover.intersects[0].object.name.substring(params.objectHover.intersects[0].object.name.indexOf("_") + 1);

                    store.dispatch(GET_SHIP, id)
                      .then(()=>{
                        component.$root.$emit('clickShip')
                      })
                    // console.log('lol_ship')
                  }
                }

                }else {

                  if ((params.objectHover.INTERSECTED != params.objectHover.intersects[0].object) && (params.objectHover.intersects.length < params.dataAboutPlanet.planets.length)) {
                    params.objectHover.count++;
                    // т.к. первым прогружается и сразу отображается как наведенное солнце, то !=1 к count, при наведение на космос(не объект все будет работать нормально)
                    if (params.objectHover.count != 1) {


                      // TODO alert('HELLO')


                      onPlanet(params.objectHover.intersects[0].object.name)


                      params.objectHover.intersects[0].object.geometry = new THREE.SphereGeometry(params.objectHover.intersects[0].object.geometry.parameters.radius + 100, 12, 12);
                      // params.objectHover.intersects[0].object.geometry.parameters.radius += 100;
                      // createTextForPalent(params.objectHover.intersects[0].object.name, params.objectHover.intersects[0].object.position.x, params.objectHover.intersects[0].object.position.y + (params.objectHover.intersects[0].object.geometry.parameters.radius + 100) * 1.25, params.objectHover.intersects[0].object.position.z, params.objectHover.intersects[0].object.geometry.parameters.radius - 50);
                      // если после наведения на одну планету мы навели на другую не попадая на пространство космоса
                      if ((params.objectHover.count >= 2) && (params.objectHover.INTERSECTED != null)) {
                        params.objectHover.INTERSECTED.geometry = new THREE.SphereGeometry(params.objectHover.INTERSECTED.geometry.parameters.radius - 100, 12, 12);
                        // params.objectHover.INTERSECTED.geometry.parameters.radius -= 100;
                      }


                    }
                    params.objectHover.INTERSECTED = params.objectHover.intersects[0].object;
                  }
                }

              }
            } else {



              // если оставить if((count >= 2) && (INTERSECTED != null)) то в таком случае при выборе перехода по камере на планету при гаведение на космос ее радиус будет уменьшаться что приведет к созданию "черной дыры"
              if((params.objectHover.count >= 2) && (params.objectHover.INTERSECTED != null) && (params.objectHover.INTERSECTED.name != 'base')){
                params.objectHover.INTERSECTED.geometry = new THREE.SphereGeometry(params.objectHover.INTERSECTED.geometry.parameters.radius - 100, 12, 12);
                // params.objectHover.INTERSECTED.geometry.parameters.radius -= 100;
                // params.objectHover.INTERSECTED.geometry.boundingSphere.radius -=100;
              }
              params.objectHover.INTERSECTED = null;
            }
          }


          params.dataForSystem.renderer.render(params.dataForSystem.scene, params.dataForSystem.camera);

          // }
          // requestAnimationFrame(function () { lookQuickly(); });
          // console.log("sleep");
          // requestAnimationFrame(function () { lookQuickly(); });
          // console.log("sleep");


          requestAnimationFrame(function () {
            look();
          });
        }

        look();



      })
};

function fillLengthPlanetCenterPlanetAndAngle(){
    for(var i = 0; i < params.dataAboutPlanet.planets.length; i++){
        params.dataAboutPlanet.planets[i].centerPlanet = Math.sqrt(Math.pow(params.dataAboutPlanet.planets[i].locationPlanetX/100000, 2) + Math.pow(params.dataAboutPlanet.planets[i].locationPlanetY/100000, 2) + Math.pow(params.dataAboutPlanet.planets[i].locationPlanetZ/100000, 2));
        params.dataAboutPlanet.planets[i].cosAngleVerticale = Math.sqrt(Math.pow(params.dataAboutPlanet.planets[i].locationPlanetX/100000, 2) + Math.pow(params.dataAboutPlanet.planets[i].locationPlanetZ/100000, 2)) / params.dataAboutPlanet.planets[i].centerPlanet;
        params.dataAboutPlanet.planets[i].delta = Math.acos(params.dataAboutPlanet.planets[i].locationPlanetX / 100000 / params.dataAboutPlanet.planets[i].centerPlanet / params.dataAboutPlanet.planets[i].cosAngleVerticale);

    }
}

function createCamera(angle, start, end, x, y, z){
    params.dataForSystem.camera = new THREE.PerspectiveCamera(angle, params.dataForSystem.width / params.dataForSystem.height, start, end);
    params.dataForSystem.camera.position.set(x, y, z);

    var div = document.getElementById('canvas-block');
    new THREE.OrbitControls(params.dataForSystem.camera, div);
}

function createLight(color) {
    params.dataForSystem.light = new THREE.AmbientLight(color);
}

function createScene() {
    params.dataForSystem.scene = new THREE.Scene();
    params.dataForSystem.loaderTexture.load('/static/space.jpg', function (texture) {
        params.dataForSystem.background = texture;
    });
}

function createRenderer(){
    // renderer = new THREE.WebGLRenderer({canvas: canvas});
    // antialias: true позволяет сгладить ребра объектов при отдолении камеры
    params.dataForSystem.renderer = new THREE.WebGLRenderer( { canvas: canvas, antialias: true } );
    params.dataForSystem.renderer.setClearColor(0x000000);
}

function createPlanet(position, i){

    params.dataForSystem.loaderTexture.load('/static/planets/'+params.dataAboutPlanet.planets[position].img, function (texture) {


        var newMaterial = new THREE.MeshBasicMaterial({map: texture});
        var newPlanet = new THREE.SphereGeometry(params.dataAboutPlanet.planets[position].rplanet/100, 12, 12);
        var newMesh = new THREE.Mesh(newPlanet, newMaterial);
        newMesh.position.x += params.dataAboutPlanet.planets[position].locationPlanetX / 100000;
        newMesh.position.y += params.dataAboutPlanet.planets[position].locationPlanetY / 100000;
        newMesh.position.z += params.dataAboutPlanet.planets[position].locationPlanetZ / 100000;
        newMesh.name = params.dataAboutPlanet.planets[position];



        // meshs.push(newMesh);
        params.dataAboutPlanet.meshs.push(newMesh);
        params.dataForSystem.scene.add(newMesh);
        // Увеличиваем pointReady на 1, указывая, что еще одна планета готова
        params.dataForSystem.pointReady++;

    });
}

function onWindowResize() {
    params.dataForSystem.camera.aspect = window.innerWidth / window.innerHeight;
    params.dataForSystem.camera.updateProjectionMatrix();
    params.dataForSystem.renderer.setSize( window.innerWidth, window.innerHeight );
}

function onDocumentMouseMove( event ) {
    // event.preventDefault();
    params.objectHover.mouse.x = ( event.clientX / window.innerWidth ) * 2 - 1;
    params.objectHover.mouse.y = - ( event.clientY / window.innerHeight ) * 2 + 1;
}

function updataPositionPlanet() {
    var start = +new Date()/6000;
    for(var i=1; i<params.dataAboutPlanet.planets.length; i++){

        params.dataAboutPlanet.meshs[i].position.x = 0 + params.dataAboutPlanet.planets[i].centerPlanet * params.dataAboutPlanet.planets[i].cosAngleVerticale * Math.cos(start + params.dataAboutPlanet.planets[i].delta);
        params.dataAboutPlanet.meshs[i].position.z = 0 + params.dataAboutPlanet.planets[i].centerPlanet * params.dataAboutPlanet.planets[i].cosAngleVerticale * Math.sin(start + params.dataAboutPlanet.planets[i].delta);


      // console.log(params.dataAboutPlanet.meshs[i].position.x + '  '+ params.dataAboutPlanet.meshs[i].position.y+ '  '+ params.dataAboutPlanet.meshs[i].position.z)


    }
}


function createAllBase(){

  getBases()
    .then(() => {

      for(var i=0; i<params.dataAboutBase.bases.length; i++){
        createBase(params.dataAboutBase.bases[i].locationBaseX, params.dataAboutBase.bases[i].locationBaseY, params.dataAboutBase.bases[i].locationBaseZ);
      }

    })
}


export function createBase(x, y, z){


  var material = new THREE.MeshBasicMaterial({color: 0xffffff, wireframe: false, vertexColors: THREE.FaceColors});
  var geometry = new THREE.BoxGeometry(50,25,50);
  for(var i = 0; i < geometry.faces.length; i++){
    geometry.faces[i].color.setRGB(Math.random(), Math.random(), Math.random());
  }
  var mesh = new THREE.Mesh(geometry, material);
  mesh.position.x += x;
  mesh.position.y += y;
  mesh.position.z += z;
  mesh.name ='base';
  params.dataForSystem.scene.add(mesh);
}


export function newPositionCameraForBase(x, y, z) {

  params.dataForSystem.camera.position.set(x-65, y+100, z+100);

}


/*
при вытягивании всей табл из бд
мы должны перебрать циклом все обьекты и для каждого из них вызвать эту фукцию
перед этим проверив принадлежит этот кораблик пользователю или нет

createObjectShip('shuttle', protoObjShip, true, false);

createObjectShip('shuttle', protoObjShip, false, false);


createObjectShip('shuttle', protoObjShip, true, true); - для создание нового корабля
* */

var ship;
var loader = new THREE.ObjectLoader();
export function createObjectShip(name, protoObjShip, createShipFirstTime) {
  loader.load("/static/models/SpaceShips/" + name + "/" + name + ".json", function (obj) {
    // obj.children[0].name = protoObjShip.id;
    // obj.children[1].name = protoObjShip.id;
    // obj.children[2].name = protoObjShip.id;
    // obj.children[3].name = protoObjShip.id;
    // obj.children[4].name = protoObjShip.id;
    // obj.children[5].name = protoObjShip.id;

    obj.children[0].name = 'ship_'+protoObjShip.id;
    obj.children[1].name = 'ship_'+protoObjShip.id;
    obj.children[2].name = 'ship_'+protoObjShip.id;
    obj.children[3].name = 'ship_'+protoObjShip.id;
    obj.children[4].name = 'ship_'+protoObjShip.id;
    obj.children[5].name = 'ship_'+protoObjShip.id;

    obj.position.set(protoObjShip.locationShipX, protoObjShip.locationShipY, protoObjShip.locationShipZ);

    obj.scale.set(.1000, .1000, .1000);
    params.dataForSystem.scene.add(obj);

    ship = new Ship(protoObjShip.locationShipX, protoObjShip.locationShipY, protoObjShip.locationShipZ,
      protoObjShip.rotationShipX, protoObjShip.rotationShipY, protoObjShip.rotationShipZ, protoObjShip.speed);

    let id = protoObjShip.id;
    params.ships.arrShips.push({ship, obj, id});

    if(createShipFirstTime){
      guiForCreateShip(ship);
    }
  });
}

/* в look

if(countShipFromReq != params.ships.arrShips.length){
далее определяем для каких id нужно вызвать метод
createObjectShip('shuttle', protoObjShip, 0, 0);
}
*/


function guiForCreateShip(ship){
  if(params.dataForSystem.lastFildGui.rotationX != null){
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationX);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationY);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationZ);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.speed);
  }

  params.dataForSystem.lastFildGui.rotationX = params.dataForSystem.gui.add(ship, 'rotationX').min(-0.001).max(0.001).step(0.0001);
  params.dataForSystem.lastFildGui.rotationY = params.dataForSystem.gui.add(ship, 'rotationY').min(-0.001).max(0.001).step(0.0001);
  params.dataForSystem.lastFildGui.rotationZ = params.dataForSystem.gui.add(ship, 'rotationZ').min(-0.001).max(0.001).step(0.0001);
  params.dataForSystem.lastFildGui.speed = params.dataForSystem.gui.add(ship, 'speed').min(0).max(10).step(0.1);

  params.dataForSystem.gui.open();
}


export function updatePositionShip(protoObjShip, rots) {
  // let objShip = params.ships.arrShips.find(obj => obj.id === protoObjShip.id);
  //
  // objShip.obj.rotation.x += rots.rotationShipX;
  // objShip.obj.rotation.y += rots.rotationShipY;
  // objShip.obj.rotation.z += rots.rotationShipZ;
  //
  // objShip.obj.position.z += Math.sin((Math.PI / 2) + objShip.obj.rotation.x) * Math.cos( objShip.obj.rotation.y) * objShip.ship.speed;
  // objShip.obj.position.x += Math.sin((Math.PI / 2) + objShip.obj.rotation.x) * Math.sin( objShip.obj.rotation.y) * objShip.ship.speed;
  // objShip.obj.position.y += Math.cos((Math.PI / 2) + objShip.obj.rotation.x) * objShip.ship.speed;
  //
  //
  // protoObjShip.locationShipX = objShip.obj.position.x;
  // protoObjShip.locationShipY = objShip.obj.position.y;
  // protoObjShip.locationShipZ = objShip.obj.position.z;
  //
  // protoObjShip.rotationShipX = objShip.obj.rotation.x;
  // protoObjShip.rotationShipY = objShip.obj.rotation.y;
  // protoObjShip.rotationShipZ = objShip.obj.rotation.z;


  // ----

  protoObjShip.rotationShipX += rots.rotationShipX;
  protoObjShip.rotationShipY += rots.rotationShipY;
  protoObjShip.rotationShipZ += rots.rotationShipZ;

  protoObjShip.locationShipZ += Math.sin((Math.PI / 2) + protoObjShip.rotationShipX) * Math.cos( protoObjShip.rotationShipY) * protoObjShip.speed / 30;
  protoObjShip.locationShipX += Math.sin((Math.PI / 2) + protoObjShip.rotationShipX) * Math.sin( protoObjShip.rotationShipY) * protoObjShip.speed / 30;
  protoObjShip.locationShipY += Math.cos((Math.PI / 2) + protoObjShip.rotationShipX) * protoObjShip.speed / 30;

  moveShip(protoObjShip)
  return protoObjShip
}

export function updatePositionShipMove(protoObjShip) {

  let objShip = params.ships.arrShips.find(obj => obj.id === protoObjShip.id);

  objShip.obj.rotation.x = protoObjShip.rotationShipX;
  objShip.obj.rotation.y = protoObjShip.rotationShipY;
  objShip.obj.rotation.z = protoObjShip.rotationShipZ;

  objShip.obj.position.z = protoObjShip.locationShipZ;
  objShip.obj.position.x = protoObjShip.locationShipX;
  objShip.obj.position.y = protoObjShip.locationShipY;
}



