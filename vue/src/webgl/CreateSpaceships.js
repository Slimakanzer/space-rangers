import { params } from "@/webgl/AllParams";

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

var loader = new THREE.ObjectLoader();
function createObjectShip(name, protoObjShip) {
  loader.load("/static/models/SpaceShips/" + name + "/" + name + ".json", function (obj) {
    obj.children[0].name = params.ships.arrShips.length;
    obj.children[1].name = params.ships.arrShips.length;
    obj.children[2].name = params.ships.arrShips.length;
    obj.children[3].name = params.ships.arrShips.length;
    obj.children[4].name = params.ships.arrShips.length;
    obj.children[5].name = params.ships.arrShips.length;

    obj.position.set(protoObjShip.locationShipX, protoObjShip.locationShipY, protoObjShip.locationShipZ);
    params.dataForSystem.scene.add(obj);
    var ship = new Ship(protoObjShip.locationShipX, protoObjShip.locationShipY, protoObjShip.locationShipZ,
      protoObjShip.rotationShipX, protoObjShip.rotationShipY, protoObjShip.rotationShipZ, protoObjShip.speed);
    params.ships.arrShips.push({ship, obj});

  });
}

var delete1, delete2, delete3;
var ship;

function createShip(name, nameFill) {
    if(nameFill=='position'){

    }else{
        params.dataForSystem.gui.remove(delete1);
        params.dataForSystem.gui.remove(delete2);
        params.dataForSystem.gui.remove(delete3);

        params.dataForSystem.lastFildGui.rotationX = params.dataForSystem.gui.add(ship, 'rotationX').min(-0.001).max(0.001).step(0.0001);
        params.dataForSystem.lastFildGui.rotationY = params.dataForSystem.gui.add(ship, 'rotationY').min(-0.001).max(0.001).step(0.0001);
        params.dataForSystem.lastFildGui.rotationZ = params.dataForSystem.gui.add(ship, 'rotationZ').min(-0.001).max(0.001).step(0.0001);
        params.dataForSystem.lastFildGui.speed = params.dataForSystem.gui.add(ship, 'speed').min(0).max(10).step(0.1);
    }
}

export function createShipForPosition(base){
  alert(JSON.stringify(base))

  createObjectShip('shuttle', base);
  if(params.dataForSystem.lastFildGui.rotationX != null){
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationX);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationY);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationZ);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.speed);
  }

  delete1 = params.dataForSystem.gui.add(ship, 'positionX').min(-1000).max(1000).step(1);
  delete2 = params.dataForSystem.gui.add(ship, 'positionY').min(-1000).max(1000).step(1);
  delete3 = params.dataForSystem.gui.add(ship, 'positionZ').min(-1000).max(1000).step(1);
}

function setPositionShip(ship){
  params.dataForSystem.gui.open();

  if(params.dataForSystem.lastFildGui.rotationX != null){
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationX);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationY);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.rotationZ);
    params.dataForSystem.gui.remove(params.dataForSystem.lastFildGui.speed);
  }

  delete1 = params.dataForSystem.gui.add(ship, 'positionX').min(-1000).max(1000).step(1);
  delete2 = params.dataForSystem.gui.add(ship, 'positionY').min(-1000).max(1000).step(1);
  delete3 = params.dataForSystem.gui.add(ship, 'positionZ').min(-1000).max(1000).step(1);
}
