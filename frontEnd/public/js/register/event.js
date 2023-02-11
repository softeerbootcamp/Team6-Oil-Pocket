import { addEvent, changeCSS, changeArrayCSS } from "../common/function"
import { fetchRegisterID, fetchValidateID } from "./fetch";
import { getRegisterFetchBody, isSamePWInputs } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;

const eventToTermOfUseBtn = ($container) => {
    const $termOfUseBtn = $container.querySelector(".registerArea__termOfUseContentBtn");
    const $termOfUseParagraph = $container.querySelector(".registerArea__termOfUseContent");
    const $termOfUseArea = $container.querySelector(".registerArea__termOfUse");

    addEvent($termOfUseBtn, [
        () => $termOfUseParagraph.innerHTML = ``,
        () => changeCSS($termOfUseParagraph, "height", "1vh"),
        () => changeCSS($termOfUseArea, "height", "12vh"),
        () => $termOfUseBtn.classList.add("clicked")
    ]);
}

const eventToSelects = ($container) => {
    const $selectgender = $container.querySelector(".registerArea__genderTitle");
    const $selectgenderValues = $container.querySelectorAll(".registerArea__genderValue");
    const $selectAge = $container.querySelector(".registerArea__ageTitle");
    const $selectAgeValues = $container.querySelectorAll(".registerArea__ageValue");

    addEvent($selectgender, [
        () => $selectgenderValues.forEach(($genderValue, index) => {
            changeCSS($genderValue, "outline", "0.2vh solid #14BD72");
            changeCSS($genderValue, "top", `${(index + 1) * 108}%`) 
        })
    ]);

    addEvent($selectAge, [
        () => $selectAgeValues.forEach(($ageValue, index) => {
            changeCSS($ageValue, "outline", "0.2vh solid #14BD72");
            changeCSS($ageValue, "top", `${(index + 1) * 108}%`);
        })
    ]);
}

const eventToSelectValues = ($container) => {
    const $genderTitle = $container.querySelector(".registerArea__genderTitle");
    const $genderValues = $container.querySelectorAll(".registerArea__genderValue");
    const $ageTitle = $container.querySelector(".registerArea__ageTitle");
    const $ageValues = $container.querySelectorAll(".registerArea__ageValue");

    $genderValues.forEach(($genderValue) => addEvent($genderValue, [
        () => $genderTitle.innerHTML = $genderValue.innerHTML,
        () => changeCSS($genderTitle, "backgroundColor", "#14BD72"),
        () => changeArrayCSS($genderValues, "top", 0)
    ]));

    $ageValues.forEach(($ageValue) => addEvent($ageValue, [
        () => $ageTitle.innerHTML = $ageValue.innerHTML,
        () => changeCSS($ageTitle, "backgroundColor", "#14BD72"),
        () => changeArrayCSS($ageValues, "top", 0)
    ]))
}

const eventToRegisterBtn = ($container) => {
    const $IDInput = $container.querySelector(".registerArea__IDArea > input")
    const $registerBtn = $container.querySelector(".registerBtn");
    const $selectAge = $container.querySelector(".registerArea__ageTitle");
    const $selectGender = $container.querySelector(".registerArea__genderTitle");
    const $pwInput = $container.querySelector("#registerArea__pwInput");
    const $pwReInput = $container.querySelector("#registerArea__pwReInput");

    addEvent($registerBtn, [() => {
        if($selectGender.innerHTML === "성별") {
            changeCSS($selectGender, "outline", "0.4vh solid red");
            setTimeout(() => changeCSS($selectGender, "outline", "0vh solid #14BD7E"), 1000);
            $selectGender.focus();
            return;
        }

        if($selectAge.innerHTML == "나이") {
            changeCSS($selectAge, "outline", "0.4vh solid red");
            setTimeout(() => changeCSS($selectAge, "outline", "0vh solid #14BD7E"), 1000);
            return;
        }

        if($pwInput.value === "") {
            changeCSS($pwInput, "outline", "0.4vh solid red");
            setTimeout(() => {
                changeCSS($pwInput, "outline", "0vh solid #14BD7E");
                $pwInput.focus();
            }, 1000);

            return ;
        }

        if($pwReInput.value === "") {
            changeCSS($pwReInput, "outline", "0.4vh solid red");
            setTimeout(() => {
                changeCSS($pwReInput, "outline", "0vh solid #14BD7E");
                $pwReInput.focus();
            }, 1000);

            return ;
        }

        if(!isSamePWInputs($pwInput, $pwReInput)) {
            $pwInput.focus();
            return;
        }

        const registerJSON = getRegisterFetchBody($IDInput, $pwInput, $selectGender, $selectAge);
        fetchRegisterID(registerJSON);
    }])
}

const eventToIDValidateBtn = ($registerContainer) => {
    const $IDValidateBtn = $registerContainer.querySelector(".IDValidateBtn");
    const $registerBtn = $registerContainer.querySelector(".registerBtn");
    const $IDInput = $registerContainer.querySelector(".registerArea__IDArea > input");

    addEvent($IDValidateBtn, [() => fetchValidateID($IDInput, $IDValidateBtn, $registerBtn)]);
}

export { eventToTermOfUseBtn, eventToIDValidateBtn, eventToSelects, eventToRegisterBtn, eventToSelectValues }