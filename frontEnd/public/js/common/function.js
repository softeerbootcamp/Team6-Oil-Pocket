const addEvent = ($target, callBackArray, eventType="click") => {
    callBackArray.forEach((callBack) => $target.addEventListener(eventType, callBack));
}

const changeCSS = ($target, key, value) => $target.style[key] = value;

const replaceChildWithFadeEffect = ($parent, $child) => {
    $parent.innerHTML = "";
    $parent.appendChild($child);
    $child.style.opacity = 1;
}

const pipe = (...functionList) => (firstParam) => 
    functionList.reduce((curValue, curFunc) => { 
        return curFunc(curValue);
    }, firstParam);

const makeLighter = ($target) => $target.style.opacity = 1;

export { addEvent, changeCSS, replaceChildWithFadeEffect, pipe, makeLighter }