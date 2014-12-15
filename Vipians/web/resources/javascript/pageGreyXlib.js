window.onload = function() {
   parent.footer.location.reload();
   changeStyleAlIngresar();
};
// assigning an unload listener disables bfcache (fastback) in Opera and Firefox
window.onunload = function() {
};

var pg=null;
// ariel
var pgNav=null;

xAddEventListener(window, 'load',
  function() {
    if (document.getElementById && document.createElement && document.body.appendChild) {
      //pg = new xPageGrey('clsPageGreyDiv', '../../resources/imagenes/mensajes/indicator_FF0000.gif', 'clsPageGreyImg', 'Espere Por Favor', 'clsPageGreyMsg');
      pg = new xPageGrey('clsPageGreyDiv', '/Vipians/resources/imagenes/mensajes/indicator_FF0000.gif', 'clsPageGreyImg', 'Espere Por Favor', 'clsPageGreyMsg');
      
      // ariel
      pgNav = new xPageGreyAAT(parent.nav1.document, parent.nav1.window, 'clsPageGreyDiv', null, 'clsPageGreyImg', null, null);
      
      // ariel - no uso evento onClick
      //xGetElementById('form1:btnBuscar').onclick = function() {
        //pg.show();
        //alert("message");
        //pg.hide();
      //};
      
//      xGetElementById('form1').onsubmit = function() { 
//        var theDiv = parent.nav1.document.getElementById('divGrey');
//        if (theDiv != null) { /*alert("theDIV ya esta en NAV .. LA MUESTRO");*/ pgNav.show(); }
//        else { /*alert("theDIV NO ESTA en NAV !!!!");*/ }
//        pg.show();
//        return true;
//      };

    }
  },
  false
);

function xPageGrey(sDivClass, sImgUrl, sImgClass, sMsg, sMsgClass)
{
  /*@cc_on
  @if (@_jscript_version < 5.5) // opacity not supported in IE until v5.5
  this.ele = null;
  @else @*/
  this.ele = document.createElement('div');
  this.ele.className = sDivClass;
  if (sImgUrl) {
    var img = document.createElement('img');
    img.src = sImgUrl;
    img.className = sImgClass;
    this.msg = document.createElement('p');
    this.msg.className = sMsgClass;
    this.msg.appendChild(img);
    
    // ariel
    this.msg.appendChild(document.createElement('br'));
    
    this.msg.appendChild(document.createTextNode(sMsg));
    document.body.appendChild(this.msg);
  }
  document.body.appendChild(this.ele);
  /*@end @*/
  this.show = function()
  {
    if (this.ele) {
      var ds = xDocSize();
      xMoveTo(this.ele, 0, 0);
      xResizeTo(this.ele, ds.w, ds.h);
      if (this.msg) {
        xMoveTo(this.msg, xScrollLeft()+(xClientWidth()-xWidth(this.msg))/2, xScrollTop()+(xClientHeight()-xHeight(this.msg))/2);
      }
    }
  };
  this.hide = function()
  {
    if (this.ele) {
      xResizeTo(this.ele, 10, 10);
      xMoveTo(this.ele, -10, -10);
      if (this.msg) {
        xMoveTo(this.msg, -xWidth(this.msg), 0);
      }
    }
  };
}

/* Compiled from X 4.09 with XC 1.02 on 02Mar07 */
function xAddEventListener(e,eT,eL,cap)
{
  if(!(e=xGetElementById(e)))return;
  eT=eT.toLowerCase();
  if(e.addEventListener)e.addEventListener(eT,eL,cap||false);
  else if(e.attachEvent)e.attachEvent('on'+eT,eL);
  else e['on'+eT]=eL;
}
function xClientHeight()
{
  var v=0,d=document,w=window;
  if(d.compatMode == 'CSS1Compat' && !w.opera && d.documentElement && d.documentElement.clientHeight)
    {v=d.documentElement.clientHeight;}
  else if(d.body && d.body.clientHeight)
    {v=d.body.clientHeight;}
  else if(xDef(w.innerWidth,w.innerHeight,d.width)) {
    v=w.innerHeight;
    if(d.width>w.innerWidth) v-=16;
  }
  return v;
}
function xClientWidth()
{
  var v=0,d=document,w=window;
  if(d.compatMode == 'CSS1Compat' && !w.opera && d.documentElement && d.documentElement.clientWidth)
    {v=d.documentElement.clientWidth;}
  else if(d.body && d.body.clientWidth)
    {v=d.body.clientWidth;}
  else if(xDef(w.innerWidth,w.innerHeight,d.height)) {
    v=w.innerWidth;
    if(d.height>w.innerHeight) v-=16;
  }
  return v;
}
function xDef()
{
  for(var i=0; i<arguments.length; ++i){if(typeof(arguments[i])=='undefined') return false;}
  return true;
}
function xDocSize()
{
  var b=document.body, e=document.documentElement;
  var esw=0, eow=0, bsw=0, bow=0, esh=0, eoh=0, bsh=0, boh=0;
  if (e) {
    esw = e.scrollWidth;
    eow = e.offsetWidth;
    esh = e.scrollHeight;
    eoh = e.offsetHeight;
  }
  if (b) {
    bsw = b.scrollWidth;
    bow = b.offsetWidth;
    bsh = b.scrollHeight;
    boh = b.offsetHeight;
  }
  return {w:Math.max(esw,eow,bsw,bow),h:Math.max(esh,eoh,bsh,boh)};
}
function xGetComputedStyle(oEle, sProp, bInt)
{
  var s, p = 'undefined';
  var dv = document.defaultView;
  if(dv && dv.getComputedStyle){
    s = dv.getComputedStyle(oEle,'');
    if (s) p = s.getPropertyValue(sProp);
  }
  else if(oEle.currentStyle) {
    var i, c, a = sProp.split('-');
    sProp = a[0];
    for (i=1; i<a.length; ++i) {
      c = a[i].charAt(0);
      sProp += a[i].replace(c, c.toUpperCase());
    }
    p = oEle.currentStyle[sProp];
  }
  else return null;
  return bInt ? (parseInt(p) || 0) : p;
}
function xGetElementById(e)
{
  if(typeof(e)=='string') {
    if(document.getElementById) e=document.getElementById(e);
    else if(document.all) e=document.all[e];
    else e=null;
  }
  return e;
}
function xHeight(e,h)
{
  if(!(e=xGetElementById(e))) return 0;
  if (xNum(h)) {
    if (h<0) h = 0;
    else h=Math.round(h);
  }
  else h=-1;
  var css=xDef(e.style);
  if (e == document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    h = xClientHeight();
  }
  else if(css && xDef(e.offsetHeight) && xStr(e.style.height)) {
    if(h>=0) {
      var pt=0,pb=0,bt=0,bb=0;
      if (document.compatMode=='CSS1Compat') {
        var gcs = xGetComputedStyle;
        pt=gcs(e,'padding-top',1);
        if (pt !== null) {
          pb=gcs(e,'padding-bottom',1);
          bt=gcs(e,'border-top-width',1);
          bb=gcs(e,'border-bottom-width',1);
        }
        else if(xDef(e.offsetHeight,e.style.height)){
          e.style.height=h+'px';
          pt=e.offsetHeight-h;
        }
      }
      h-=(pt+pb+bt+bb);
      if(isNaN(h)||h<0) return;
      else e.style.height=h+'px';
    }
    h=e.offsetHeight;
  }
  else if(css && xDef(e.style.pixelHeight)) {
    if(h>=0) e.style.pixelHeight=h;
    h=e.style.pixelHeight;
  }
  return h;
}
function xLeft(e, iX)
{
  if(!(e=xGetElementById(e))) return 0;
  var css=xDef(e.style);
  if (css && xStr(e.style.left)) {
    if(xNum(iX)) e.style.left=iX+'px';
    else {
      iX=parseInt(e.style.left);
      if(isNaN(iX)) iX=xGetComputedStyle(e,'left',1);
      if(isNaN(iX)) iX=0;
    }
  }
  else if(css && xDef(e.style.pixelLeft)) {
    if(xNum(iX)) e.style.pixelLeft=iX;
    else iX=e.style.pixelLeft;
  }
  return iX;
}
xLibrary={version:'4.09',license:'GNU LGPL',url:'http://cross-browser.com/'};
function xMoveTo(e,x,y)
{
  xLeft(e,x);
  xTop(e,y);
}
function xNum()
{
  for(var i=0; i<arguments.length; ++i){if(isNaN(arguments[i]) || typeof(arguments[i])!='number') return false;}
  return true;
}
function xResizeTo(e,w,h)
{
  xWidth(e,w);
  xHeight(e,h);
}
function xScrollLeft(e, bWin)
{
  var offset=0;
  if (!xDef(e) || bWin || e == document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    var w = window;
    if (bWin && e) w = e;
    if(w.document.documentElement && w.document.documentElement.scrollLeft) offset=w.document.documentElement.scrollLeft;
    else if(w.document.body && xDef(w.document.body.scrollLeft)) offset=w.document.body.scrollLeft;
  }
  else {
    e = xGetElementById(e);
    if (e && xNum(e.scrollLeft)) offset = e.scrollLeft;
  }
  return offset;
}
function xScrollTop(e, bWin)
{
  var offset=0;
  if (!xDef(e) || bWin || e == document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    var w = window;
    if (bWin && e) w = e;
    if(w.document.documentElement && w.document.documentElement.scrollTop) offset=w.document.documentElement.scrollTop;
    else if(w.document.body && xDef(w.document.body.scrollTop)) offset=w.document.body.scrollTop;
  }
  else {
    e = xGetElementById(e);
    if (e && xNum(e.scrollTop)) offset = e.scrollTop;
  }
  return offset;
}
function xStr(s)
{
  for(var i=0; i<arguments.length; ++i){if(typeof(arguments[i])!='string') return false;}
  return true;
}
function xTop(e, iY)
{
  if(!(e=xGetElementById(e))) return 0;
  var css=xDef(e.style);
  if(css && xStr(e.style.top)) {
    if(xNum(iY)) e.style.top=iY+'px';
    else {
      iY=parseInt(e.style.top);
      if(isNaN(iY)) iY=xGetComputedStyle(e,'top',1);
      if(isNaN(iY)) iY=0;
    }
  }
  else if(css && xDef(e.style.pixelTop)) {
    if(xNum(iY)) e.style.pixelTop=iY;
    else iY=e.style.pixelTop;
  }
  return iY;
}
function xWidth(e,w)
{
  if(!(e=xGetElementById(e))) return 0;
  if (xNum(w)) {
    if (w<0) w = 0;
    else w=Math.round(w);
  }
  else w=-1;
  var css=xDef(e.style);
  if (e == document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    w = xClientWidth();
  }
  else if(css && xDef(e.offsetWidth) && xStr(e.style.width)) {
    if(w>=0) {
      var pl=0,pr=0,bl=0,br=0;
      if (document.compatMode=='CSS1Compat') {
        var gcs = xGetComputedStyle;
        pl=gcs(e,'padding-left',1);
        if (pl !== null) {
          pr=gcs(e,'padding-right',1);
          bl=gcs(e,'border-left-width',1);
          br=gcs(e,'border-right-width',1);
        }
        else if(xDef(e.offsetWidth,e.style.width)){
          e.style.width=w+'px';
          pl=e.offsetWidth-w;
        }
      }
      w-=(pl+pr+bl+br);
      if(isNaN(w)||w<0) return;
      else e.style.width=w+'px';
    }
    w=e.offsetWidth;
  }
  else if(css && xDef(e.style.pixelWidth)) {
    if(w>=0) e.style.pixelWidth=w;
    w=e.style.pixelWidth;
  }
  return w;
}



// ariel -------------------------------------------------------------- FUNCIONES CAMBIADAS (NUEVAS)

function xPageGreyAAT(doc, win, sDivClass, sImgUrl, sImgClass, sMsg, sMsgClass)
{
  /*@cc_on
  @if (@_jscript_version < 5.5) // opacity not supported in IE until v5.5
  this.ele = null;
  @else @*/
  this.docu = doc;
  this.wind = win;
  this.ele = this.docu.createElement('div');
  this.ele.className = sDivClass;
  this.ele.id = 'divGrey';
  if (sImgUrl) {
    var img = this.docu.createElement('img');
    img.src = sImgUrl;
    img.className = sImgClass;
    this.msg = this.docu.createElement('p');
    this.msg.className = sMsgClass;
    this.msg.appendChild(img);

    // ariel
    this.msg.appendChild(this.docu.createElement('br'));

    this.msg.appendChild(this.docu.createTextNode(sMsg));
    this.docu.body.appendChild(this.msg);
  }
  
//////////--------------------------------------------------------//////////

// ariel
  var theDiv = this.docu.getElementById('divGrey');
  if (theDiv == null) {
    //this.docu.body.removeChild(theDiv);
    //alert("NO existe theDIV .. LA CREO");
    this.docu.body.appendChild(this.ele);
  }
  else {
    //alert("existe theDIV .. la BORRO y la vuelvo a CREAR");
    //alert("SIZE ::::::::::::::::::::::::: " + theDiv.style.height);
    if (theDiv.style.height < 1) {
      this.docu.body.removeChild(theDiv);
      this.docu.body.appendChild(this.ele);
    }
    
  }
  /*@end @*/
  this.show = function()
  {
    if (this.ele) {
      var ds = xDocSizeAAT(this.docu);
      xMoveToAAT(this.docu, this.ele, 0, 0);
      xResizeToAAT(this.docu, this.ele, ds.w, ds.h);
      if (this.msg) {
        xMoveToAAT(this.docu, this.msg, xScrollLeftAAT()+(xClientWidthAAT(this.docu, this.wind)-xWidthAAT(this.docu, this.msg))/2, xScrollTop()+(xClientHeightAAT(this.docu, this.wind)-xHeightAAT(this.docu, this.msg))/2);
      }
    }
  };
  this.hide = function()
  {
    if (this.ele) {
      xResizeToAAT(this.docu, this.ele, 10, 10);
      xMoveToAAT(this.docu, this.ele, -10, -10);
      if (this.msg) {
        xMoveToAAT(this.docu, this.msg, -xWidthAAT(this.docu, this.msg), 0);
      }
    }
  };
}

function xClientHeightAAT(doc, win)
{
  //var v=0,d=document,w=window;
  var v=0, d=doc, w=win;
  if(d.compatMode == 'CSS1Compat' && !w.opera && d.documentElement && d.documentElement.clientHeight)
    {v=d.documentElement.clientHeight;}
  else if(d.body && d.body.clientHeight)
    {v=d.body.clientHeight;}
  else if(xDef(w.innerWidth,w.innerHeight,d.width)) {
    v=w.innerHeight;
    if(d.width>w.innerWidth) v-=16;
  }
  return v;
}

function xClientWidthAAT(doc, win)
{
  //var v=0,d=document,w=window;
  var v=0, d=doc, w=win;
  if(d.compatMode == 'CSS1Compat' && !w.opera && d.documentElement && d.documentElement.clientWidth)
    {v=d.documentElement.clientWidth;}
  else if(d.body && d.body.clientWidth)
    {v=d.body.clientWidth;}
  else if(xDef(w.innerWidth,w.innerHeight,d.height)) {
    v=w.innerWidth;
    if(d.height>w.innerHeight) v-=16;
  }
  return v;
}

function xDocSizeAAT(doc)
{
  //var b=document.body, e=document.documentElement;
  var b=doc.body, e=doc.documentElement;
  var esw=0, eow=0, bsw=0, bow=0, esh=0, eoh=0, bsh=0, boh=0;
  if (e) {
    esw = e.scrollWidth;
    eow = e.offsetWidth;
    esh = e.scrollHeight;
    eoh = e.offsetHeight;
  }
  if (b) {
    bsw = b.scrollWidth;
    bow = b.offsetWidth;
    bsh = b.scrollHeight;
    boh = b.offsetHeight;
  }
  return {w:Math.max(esw,eow,bsw,bow),h:Math.max(esh,eoh,bsh,boh)};
}





function xGetComputedStyleAAT(doc, oEle, sProp, bInt)
{
  var s, p = 'undefined';
  var dv = doc.defaultView;
  if(dv && dv.getComputedStyle){
    s = dv.getComputedStyle(oEle,'');
    if (s) p = s.getPropertyValue(sProp);
  }
  else if(oEle.currentStyle) {
    var i, c, a = sProp.split('-');
    sProp = a[0];
    for (i=1; i<a.length; ++i) {
      c = a[i].charAt(0);
      sProp += a[i].replace(c, c.toUpperCase());
    }
    p = oEle.currentStyle[sProp];
  }
  else return null;
  return bInt ? (parseInt(p) || 0) : p;
}

function xGetElementByIdAAT(doc,e)
{
  if(typeof(e)=='string') {
    if(doc.getElementById) e=doc.getElementById(e);
    else if(doc.all) e=doc.all[e];
    else e=null;
  }
  return e;
}

function xHeightAAT(doc,e,h)
{
  if(!(e=xGetElementByIdAAT(doc,e))) return 0;
  if (xNum(h)) {
    if (h<0) h = 0;
    else h=Math.round(h);
  }
  else h=-1;
  var css=xDef(e.style);
  if (e == doc || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    h = xClientHeightAAT();
  }
  else if(css && xDef(e.offsetHeight) && xStr(e.style.height)) {
    if(h>=0) {
      var pt=0,pb=0,bt=0,bb=0;
      if (doc.compatMode=='CSS1Compat') {
        var gcs = xGetComputedStyleAAT;
        pt=gcs(doc,e,'padding-top',1);
        if (pt !== null) {
          pb=gcs(doc,e,'padding-bottom',1);
          bt=gcs(doc,e,'border-top-width',1);
          bb=gcs(doc,e,'border-bottom-width',1);
        }
        else if(xDef(e.offsetHeight,e.style.height)){
          e.style.height=h+'px';
          pt=e.offsetHeight-h;
        }
      }
      h-=(pt+pb+bt+bb);
      if(isNaN(h)||h<0) return;
      else e.style.height=h+'px';
    }
    h=e.offsetHeight;
  }
  else if(css && xDef(e.style.pixelHeight)) {
    if(h>=0) e.style.pixelHeight=h;
    h=e.style.pixelHeight;
  }
  return h;
}

function xLeftAAT(doc, e, iX)
{
  if(!(e=xGetElementByIdAAT(doc,e))) return 0;
  var css=xDef(e.style);
  if (css && xStr(e.style.left)) {
    if(xNum(iX)) e.style.left=iX+'px';
    else {
      iX=parseInt(e.style.left);
      if(isNaN(iX)) iX=xGetComputedStyleAAT(doc, e,'left',1);
      if(isNaN(iX)) iX=0;
    }
  }
  else if(css && xDef(e.style.pixelLeft)) {
    if(xNum(iX)) e.style.pixelLeft=iX;
    else iX=e.style.pixelLeft;
  }
  return iX;
}

function xMoveToAAT(doc,e,x,y)
{
  xLeftAAT(doc,e,x);
  xTopAAT(doc,e,y);
}

function xResizeToAAT(doc,e,w,h)
{
  xWidthAAT(doc,e,w);
  xHeightAAT(doc,e,h);
}

function xScrollLeftAAT(win, e, bWin)
{
  var offset=0;
  if (!xDef(e) || bWin || e == w.document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    var w = win;
    if (bWin && e) w = e;
    if(w.document.documentElement && w.document.documentElement.scrollLeft) offset=w.document.documentElement.scrollLeft;
    else if(w.document.body && xDef(w.document.body.scrollLeft)) offset=w.document.body.scrollLeft;
  }
  else {
    e = xGetElementByIdAAT(win.document,e);
    if (e && xNum(e.scrollLeft)) offset = e.scrollLeft;
  }
  return offset;
}

function xScrollTopAAT(win, e, bWin)
{
  var offset=0;
  if (!xDef(e) || bWin || e == w.document || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    var w = win;
    if (bWin && e) w = e;
    if(w.document.documentElement && w.document.documentElement.scrollTop) offset=w.document.documentElement.scrollTop;
    else if(w.document.body && xDef(w.document.body.scrollTop)) offset=w.document.body.scrollTop;
  }
  else {
    e = xGetElementByIdAAT(w.document,e);
    if (e && xNum(e.scrollTop)) offset = e.scrollTop;
  }
  return offset;
}

function xTopAAT(doc, e, iY)
{
  if(!(e=xGetElementByIdAAT(doc, e))) return 0;
  var css=xDef(e.style);
  if(css && xStr(e.style.top)) {
    if(xNum(iY)) e.style.top=iY+'px';
    else {
      iY=parseInt(e.style.top);
      if(isNaN(iY)) iY=xGetComputedStyleAAT(doc, e,'top',1);
      if(isNaN(iY)) iY=0;
    }
  }
  else if(css && xDef(e.style.pixelTop)) {
    if(xNum(iY)) e.style.pixelTop=iY;
    else iY=e.style.pixelTop;
  }
  return iY;
}

function xWidthAAT(doc,e,w)
{
  if(!(e=xGetElementByIdAAT(doc,e))) return 0;
  if (xNum(w)) {
    if (w<0) w = 0;
    else w=Math.round(w);
  }
  else w=-1;
  var css=xDef(e.style);
  if (e == doc || e.tagName.toLowerCase() == 'html' || e.tagName.toLowerCase() == 'body') {
    w = xClientWidth();
  }
  else if(css && xDef(e.offsetWidth) && xStr(e.style.width)) {
    if(w>=0) {
      var pl=0,pr=0,bl=0,br=0;
      if (doc.compatMode=='CSS1Compat') {
        var gcs = xGetComputedStyleAAT;
        pl=gcs(doc,e,'padding-left',1);
        if (pl !== null) {
          pr=gcs(doc,e,'padding-right',1);
          bl=gcs(doc,e,'border-left-width',1);
          br=gcs(doc,e,'border-right-width',1);
        }
        else if(xDef(e.offsetWidth,e.style.width)){
          e.style.width=w+'px';
          pl=e.offsetWidth-w;
        }
      }
      w-=(pl+pr+bl+br);
      if(isNaN(w)||w<0) return;
      else e.style.width=w+'px';
    }
    w=e.offsetWidth;
  }
  else if(css && xDef(e.style.pixelWidth)) {
    if(w>=0) e.style.pixelWidth=w;
    w=e.style.pixelWidth;
  }
  return w;
}

// ------------------------------------------------------------------------------- FIN FUNCIONES CAMBIADAS