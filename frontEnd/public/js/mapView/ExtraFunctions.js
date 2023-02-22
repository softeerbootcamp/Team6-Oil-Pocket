const brandLogoMapper = (brandName) => {
    switch(brandName) {
        case "알뜰":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/알뜰.png";
        case "오일뱅크":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/오일뱅크.png";
        case "E1":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/E1.png";
        case "ex-OIL":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/ex.png";
        case "ex":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/ex.png";
        case "GS":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/GS.png";
        case "S-Oil":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/S-Oil.png";
        case "SK":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/SK_Gas.png";
        case "NH-OIL":
            return "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/주유소+로고_0213/NH-OIL.png";
    }
    return "./img/GasStation_Image/custom.png";
}

function ResetOptionButton(){
    $("#sort_GG").css("opacity", "0.5");
    $("#sort_HH").css("opacity", "0.5");
    $("#sort_LL").css("opacity", "0.5");
}

function transStId(stId){
    switch(stId){
        case 'S-Oil': return 'S-OIL';
        case '오일뱅크' : return '현대오일뱅크';
        case '알뜰' : return '알뜰주유소';
        case 'ex-OIL' : return '알뜰(ex)';
        case 'NH-OIL' : return 'NH-OIL';
        case 'SK' : return 'SK'; 
        case 'GS' : return 'GS칼텍스';
        case '자가상표' : return '자가상표';
        case 'E1' : return 'E1';
    }
    return '자가상표';
}

function SelectStIdLogo(stId, name, radius){
    return `
        <div class='main__ResultList'> 
            <img class='main__ResultList__Title_Logo' src = ${brandLogoMapper(stId)}>
            <div class='main__ResultList_TitlenContents'>
                <div class='main__ResultList__Title'>  
                    <div id="stName">${name}</div>
                    <div id="stRadius">${Math.round(radius*10)/10}km</div>
                </div>
                
    `;
}

function highhHsaleInfo(Resultelem) {
    return `<div class='main__ResultList__Contents'>
            휘발유: <span> ${Resultelem.hhPrice == 0 ? '-' : Resultelem.hhPrice} </span>
            <i class="fa-solid fa-circle-check"></i>&nbsp;
            경유: <span> ${Resultelem.ggPrice == 0 ? '-' : Resultelem.ggPrice} </span>&nbsp;&nbsp;
            LPG: <span> ${Resultelem.llPrice == 0 ? '-' : Resultelem.llPrice} </span></div></div></div>`;
}

function nohHsaleInfo(Resultelem) {
    return `<div class='main__ResultList__Contents'>
            휘발유: <span> ${Resultelem.hhPrice == 0 ? '-' : Resultelem.hhPrice} </span>&nbsp;&nbsp;
            경유: <span> ${Resultelem.ggPrice == 0 ? '-' : Resultelem.ggPrice} </span>&nbsp;&nbsp;
            LPG: <span> ${Resultelem.llPrice == 0 ? '-' : Resultelem.llPrice} </span></div></div></div>`;
}


function FillSTDetail(ResultArrayElem){

    FillNameonDetail(ResultArrayElem);

    FillOilPriceonDetail(ResultArrayElem);

    FillBasiconDetail(ResultArrayElem);
   
    FillPriceTableonDetail(ResultArrayElem);
}

function FillNameonDetail(ResultArrayElem){
    const ST_name = document.getElementById("GSTdetail__Name");
    ST_name.innerHTML = ResultArrayElem.name;
}

function FillOilPriceonDetail(ResultArrayElem){
    const ST_Price = document.getElementsByClassName("main__GSTdetail__Contents__Oilprice");
    var PriceinnerHtml = "";
    PriceinnerHtml += "<i class='fa-solid fa-gas-pump'></i>";
    if(ResultArrayElem.hhPrice != 0){
        PriceinnerHtml += "휘발유 : " + ResultArrayElem.hhPrice + "&nbsp&nbsp";
    }
    if(ResultArrayElem.ggPrice != 0) {
        PriceinnerHtml += "경유 : " + ResultArrayElem.ggPrice; + "&nbsp&nbsp";
    }
    if(ResultArrayElem.llPrice != 0) {
        PriceinnerHtml += "LPG : " + ResultArrayElem.llPrice;
    }
    ST_Price[0].innerHTML = PriceinnerHtml;
}

function FillBasiconDetail(ResultArrayElem){
    FillBasicAddronDetail(ResultArrayElem);
    FillBasicPhoneonDetail(ResultArrayElem);
    FillBasicUrlonDetail(ResultArrayElem);
}

function FillBasicAddronDetail(ResultArrayElem){
    const ST_addr = document.getElementById("GSTdetail_addr");
    ST_addr.innerHTML = `${ResultArrayElem.upperAddrName} ${ResultArrayElem.middleAddrName} ${ResultArrayElem.lowerAddrName}
                        ${ResultArrayElem.detailAddrName} ${ResultArrayElem.firstNo}
                        ( ${ResultArrayElem.roadName} ${ResultArrayElem.buildingNo1} )`
}

function FillBasicPhoneonDetail(ResultArrayElem){
    const ST_phone = document.getElementById("GSTdetail_phone");
    var telNolen = ResultArrayElem.telNo.length;
    if(ResultArrayElem.telNo.includes('02')) {
        var psTelNo = ResultArrayElem.telNo.slice(0, 2) + '-' + ResultArrayElem.telNo.slice(2, telNolen-4) + '-' + ResultArrayElem.telNo.slice(telNolen-4, telNolen);
    }
    else {
        var psTelNo = ResultArrayElem.telNo.slice(0, 3) + '-' + ResultArrayElem.telNo.slice(3, telNolen-4) + '-' + ResultArrayElem.telNo.slice(telNolen-4, telNolen);
    }

    ST_phone.innerHTML = psTelNo;
}

function FillBasicUrlonDetail(ResultArrayElem){
    const ST_url = document.getElementById("GSTdetail_url");
    ST_url.innerHTML = `${transStidtoUrl(ResultArrayElem.stId)}`;
    if(transStidtoUrl(ResultArrayElem.stId) == '홈페이지가 존재하지 않습니다.') {
        ST_url.style.color = 'lightgrey';
        ST_url.style.pointerEvents = 'none';
    }
    else {
        ST_url.style.pointerEvents = 'auto';
        ST_url.style.color = '#1ede88';
        ST_url.setAttribute('href', transStidtoUrl(ResultArrayElem.stId));
    }
}

function transStidtoUrl(stId){
    switch(stId){
        case 'S-Oil': return 'https://www.s-oil.com/Default.aspx';
        case '오일뱅크' : return 'https://www.oilbank.co.kr/main/index.do';
        case '알뜰' : return 'https://ecos.knoc.co.kr/';
        case 'ex-OIL' : return 'https://ecos.knoc.co.kr/';
        case 'NH-OIL' : return '홈페이지가 존재하지 않습니다.';
        case 'SK' : return 'http://www.skenergy.com/'; 
        case 'GS' : return 'https://www.gscaltex.com/kr/';
        case '자가상표' : return '홈페이지가 존재하지 않습니다.';
        case 'E1' : return 'https://www.e1.co.kr/ko/main';
    }
    return '홈페이지가 존재하지 않습니다.';
}
function FillPriceTableonDetail(ResultArrayElem){
    const ST_PriceTable = document.getElementsByClassName("main__GSTdetail__Contents__OilPriceTable__Contents");
    ST_PriceTable[0].innerHTML = "";
    if(ResultArrayElem.hhPrice != 0){
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = addHpriceTotable(ResultArrayElem);
        ST_PriceTable[0].append(ST_OilPrice);
    }
    
    if(ResultArrayElem.ggPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = addGpriceTotable(ResultArrayElem);
        ST_PriceTable[0].append(ST_OilPrice);
    }

    if(ResultArrayElem.highHhPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = addHHpriceTotable(ResultArrayElem);
        ST_PriceTable[0].append(ST_OilPrice);
    }

    if(ResultArrayElem.llPrice != 0) {
        const ST_OilPrice = document.createElement("div");
        ST_OilPrice.classList.add("main__GSTdetail__Contents__OilPriceTable__Contents__Row");
        ST_OilPrice.innerHTML = addLPGpriceTotable(ResultArrayElem);
        ST_PriceTable[0].append(ST_OilPrice);
    }
}
function addHpriceTotable(ResultArrayElem){
    return `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
            휘발유
            </div>
            <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                <span id="Contents_HH">${ResultArrayElem.hhPrice}</span>
            </div>`
}

function addGpriceTotable(ResultArrayElem){
    return `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
            경유
            </div>
            <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                <span id="Contents_HH">${ResultArrayElem.ggPrice}</span>
            </div>`
}

function addHHpriceTotable(ResultArrayElem){
    return `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
            고급 휘발유
            </div>
            <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                <span id="Contents_HH">${ResultArrayElem.highHhPrice}</span>
            </div>`
}

function addLPGpriceTotable(ResultArrayElem){
    return `<div class="main__GSTdetail__Contents__OilPriceTable__Contents__Oil">
            LPG
            </div>
            <div class="main__GSTdetail__Contents__OilPriceTable__Contents__Price">
                <span id="Contents_HH">${ResultArrayElem.llPrice}</span>
            </div>`
}

function hnHhPrice(Resultelem){
    return `<div class='main__ResultList__Contents'>
            휘발유: <span>${Resultelem.hhPrice == 0 ? '-' : Resultelem.hhPrice}</span>
            <i class="fa-solid fa-circle-check"></i>
            </div></div></div>`;
}

function onlyhPrice(Resultelem){
    return `<div class='main__ResultList__Contents'>
            휘발유: <span>${Resultelem.hhPrice == 0 ? '-' : Resultelem.hhPrice}</span>
            </div></div></div>`;
}
export {brandLogoMapper, ResetOptionButton, transStId, SelectStIdLogo, highhHsaleInfo, nohHsaleInfo, FillSTDetail, hnHhPrice, onlyhPrice};