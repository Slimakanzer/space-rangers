import { planets, bases } from "@/services/planets";
import { ships } from "@/services/ships";

export var params = {
    dataAboutPlanet: {
        meshs: [],
        planets: planets
    },

    dataAboutBase: {
        bases: bases
    },
    dataForSystem: {
        width: window.innerWidth,
        height: window.innerHeight,
        loaderTexture: new THREE.TextureLoader(),
        loaderFont: new THREE.FontLoader(),
        camera: null,
        scene: null,
        renderer: null,
        gui: null,
        lastFildGui: {
            rotationX: null,
            rotationY: null,
            rotationZ: null,
            speed: null
        },
        pointReady: 0,
        background: null
    },
    //Объект зависания (над которым зависаем)
    objectHover: {
        mouse: new THREE.Vector2(),
        INTERSECTED: null,
        intersects: null,
        raycaster: null,
        count: planets.length
    },
    ships: {
        ships: ships,
        arrShips: [],
        count: 0
    }
};
