import { addEvent, giveErrorStyle, _$ } from "../common/function";
import { fetchLoginID } from "./fetch";

const eventToLoginBtn = ($container) => {
    
    const $loginBtn = _$("#loginArea__loginBtn", $container);
    const $IDInput = _$(".loginArea__IDInput", $container);
    const $PWInput = _$(".loginArea__PWInput", $container);

    addEvent($loginBtn, [() => {
        if($IDInput.value === "") {
            giveErrorStyle($IDInput, "outline", "0.0vh solid #14BD7E", "0.2vh solid red", 1000);

            return;
        }

        if($PWInput.value === "") {
            giveErrorStyle($PWInput, "outline", "#0.0vh solid #14BD7E", "0.2vh solid red", 1000);

            return;
        }

        fetchLoginID($IDInput, $PWInput)
    }]);
}

export { eventToLoginBtn }