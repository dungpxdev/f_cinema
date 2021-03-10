var heart = document.getElementsByClassName('icon-heart');
var star= document.getElementsByClassName("star");
var use = document.getElementsByClassName('uses');
var Fav = document.getElementsByClassName('Favorites');
var Expand = document.getElementsByClassName('Expand')[0];
var ty = document.getElementsByClassName('ty')[0];
var Pro = document.getElementsByClassName('pro')[0];
var Ipro = document.getElementsByClassName('iconpro')[0];
var menuI = document.getElementsByClassName('hearted');
var Page = document.getElementsByClassName('Page')[0];
var Page2 = document.getElementsByClassName('Page2')[0];
var pageCon =  document.getElementsByClassName('PageCon')[0];
var leftA = document.getElementsByClassName('arrowL')[0];
var rightA = document.getElementsByClassName('arrowR')[0];
var BG = document.getElementsByClassName('HeroImg');
var h3 = document.getElementsByTagName('h3');
var imdb = document.getElementsByClassName('imdb');
var background= document.getElementsByClassName('background');
var videoH= document.getElementsByClassName('videoPlayer')[0];
var videoE = document.getElementsByClassName('closeV')[0];
var playVideo = document.getElementsByClassName('Video');
var iframe = document.getElementsByClassName('videoH')[0];
var searchs = document.getElementsByClassName('search')[0];
var proclose = document.getElementsByClassName('proclose')[0];
var closepro = document.getElementsByClassName('closepro')[0];
document.addEventListener('DOMContentLoaded', function() {
    start();
}, false);
Pro.addEventListener('click',menu,false);
closepro.addEventListener('click',menudown,false);
leftA.addEventListener('click',left,false);
rightA.addEventListener('click',right,false);
videoE.addEventListener('mouseover',videoOver,false);
videoE.addEventListener('mouseleave',videoOff,false);
videoE.addEventListener('click',videoC,false);
var films = new Array("THE MARTIAN","THE MAZE RUNNER","INTERSTELLAR","THE WALK","INSIDE OUT","KINGSMAN","EDGE OF TOMORROW","JAMES BOND, SPECTRE");
var fullbg = new Array("https://i.imgur.com/SJeJpyt.png","https://i.imgur.com/1l9Wsfg.png","https://i.imgur.com/J52ELal.png","https://i.imgur.com/l4z6xXd.png","https://i.imgur.com/1vML5IK.png","https://i.imgur.com/zekn1FL.png","https://i.imgur.com/2UZuGl5.png","https://i.imgur.com/3SjXfIy.png");
var rate = new Array("8.7","6.8","8.7","7.9","8.5","7.8","7.9","NONE");
var favoriteA = new Array("#d46060","#d46060","#d46060","#d46060","#d46060","#d46060","#d46060","#d46060");
var trailerMovie = new Array("https://www.youtube.com/embed/ej3ioOneTy8","https://www.youtube.com/embed/AwwbhhjQ9Xk","https://www.youtube.com/embed/zSWdZVtXT7E","https://www.youtube.com/embed/4W6byFcD5uE","https://www.youtube.com/embed/seMwpP0yeu4","https://www.youtube.com/embed/kl8F-8tR8to","https://www.youtube.com/embed/vw61gCe2oqI","https://www.youtube.com/embed/LTDaET-JweU");
var video = new Array("8.7","6.8","8.7","7.9","8.5","7.8","7.9","NONE");
var filmsBG = new Array("http://t2conline.com/wp-content/uploads/2015/08/5851.jpg","http://mazerunnerguide.com/wp-content/uploads/2014/08/The-Maze-Runner.jpg","https://www.wired.com/wp-content/uploads/2014/10/ut_interstellar2_f.png","http://cdn1-www.comingsoon.net/assets/uploads/2015/06/TheWalkBanner.jpg","http://moviecitynews.com/wp-content/uploads/2015/06/inside-out-651.jpg","http://kulturbloggen.com/wp-content/uploads/2015/01/Kingsman6.jpg","http://www.blackvibes.com/images/blogs/12-2013/27613-tom-cruise-stars-summ.jpg","http://kulturbloggen.com/wp-content/uploads/2015/07/spectre.jpg");
var filmsA = -1;
var up = -100;
var down = 100;
var clicked = 0;
var clickedP = 0;
var menuplus = -1;
var margin = 0;
var attri = 0;
var end = 0;
var startR = -1;
for(i = 0;i < playVideo.length; i++){
	Fav[i].addEventListener('click',profiles,false);
	}
for(p = 0;p < playVideo.length; p++){
	playVideo[p].addEventListener('click',trailer,false);
	}
function trailer(){
	videoH.style.top="0vw";
	}
function videoC(){
	videoH.style.top="-170vw";
	}
function videoOff(){
	videoH.style.boxShadow="0px 0px 0px 0.5vw #FFF";
	}
function videoOver(){
	videoH.style.boxShadow="0px 0px 0px 0.5vw #2ac3fb";
	}			
function start(){
	filmsA++;
				h3[0].innerHTML=films[filmsA];	
			BG[0].style.background="url("+ filmsBG[filmsA] +")";	
			BG[0].style.backgroundSize="cover";
						imdb[1].innerHTML=rate[filmsA];	
			background[0].style.background="url("+ fullbg[filmsA] +")";
			background[0].style.backgroundSize="cover";	
	}
	function left(){
		if(end < 7){
			end ++;
							rightA.style.opacity="1";
		if(clickedP == 0){
		clickedP = 1;
		filmsA ++;
		Page2.style.transition="ease 0s";
		Page.style.top= "-100vh";
		Page2.style.top= "200vh";
		    setTimeout(function () {
				Page2.style.transition="ease 1s";
			Page2.style.top= "0vh";
			Fav[1].style.background="#2ac3fb";
			h3[1].innerHTML=films[filmsA];	
			BG[1].style.background="url("+ filmsBG[filmsA] +")";	
			BG[1].style.backgroundSize="cover";
						imdb[1].innerHTML=rate[filmsA];	
			background[0].style.background="url("+ fullbg[filmsA] +")";
			background[0].style.backgroundSize="cover";	
			iframe.src=trailerMovie[filmsA];		
    }, 300);
		}else if(clickedP == 1){
			clickedP = 0;
			filmsA ++;
			Page.style.transition="ease 0s";
			Page.style.top= "100vh";
			Page2.style.top= "-200vh";
					    setTimeout(function () {
				Page.style.transition="ease 1s";
			Page.style.top= "0vh";
				   Page.style.transition="ease 1s";
			Page.style.top= "0vh";
			Fav[0].style.background="#2ac3fb";
			h3[0].innerHTML=films[filmsA];
			BG[0].style.background="url("+ filmsBG[filmsA] +")";
			BG[0].style.backgroundSize="cover";	
			imdb[0].innerHTML=rate[filmsA];		
			background[0].style.background="url("+ fullbg[filmsA] +")";
			background[0].style.backgroundSize="cover";
			iframe.src=trailerMovie[filmsA];	
    }, 300);
			}
		}else{
			leftA.style.opacity="0";
			}
		}
	function right(){
		if(end > 0){
			end --;
			leftA.style.opacity="1";
		if(clickedP == 0){
		clickedP = 1;
		filmsA --;
		Page2.style.transition="ease 0s";
		Page.style.top= "100vh";
		Page2.style.top= "-200vh";
		    setTimeout(function () {
				Page2.style.transition="ease 1s";
			Page2.style.top= "0vh";
			h3[1].innerHTML=films[filmsA];	
			BG[1].style.background="url("+ filmsBG[filmsA] +")";	
			BG[1].style.backgroundSize="cover";
						imdb[1].innerHTML=rate[filmsA];	
			background[0].style.background="url("+ fullbg[filmsA] +")";
			background[0].style.backgroundSize="cover";		
			iframe.src=trailerMovie[filmsA];		
    }, 300);
		}else if(clickedP == 1){
			clickedP = 0;
			filmsA --;
			Page.style.transition="ease 0s";
			Page.style.top= "-100vh";
			Page2.style.top= "200vh";
					    setTimeout(function () {
				Page.style.transition="ease 1s";
			Page.style.top= "0vh";
				   Page.style.transition="ease 1s";
			Page.style.top= "0vh";
			h3[0].innerHTML=films[filmsA];
			BG[0].style.background="url("+ filmsBG[filmsA] +")";
			BG[0].style.backgroundSize="cover";	
			imdb[0].innerHTML=rate[filmsA];		
			background[0].style.background="url("+ fullbg[filmsA] +")";
			background[0].style.backgroundSize="cover";	
			iframe.src=trailerMovie[filmsA];	
    }, 300);
			}
				}else{
			rightA.style.opacity="0";
			}
		}		
function menu(){
	this.style.width="250vw";
	this.style.height="250vw";
	this.style.right="-125vw";
	this.style.top="-125vw";
	Ipro.style.opacity="0";
	searchs.style.display="block";
	closepro.style.zIndex="9999999999999999";
	setTimeout(function(){
	searchs.style.opacity="1";
	searchs.style.top="0";
	closepro.style.opacity="1";
	},800);
	}
function menudown(){
	searchs.style.opacity="0";
	searchs.style.top="5vw";
	closepro.style.opacity="0";
		setTimeout(function(){
		Pro.style.width="4vw";
	Pro.style.height="4vw";
	Pro.style.right="1vw";
	Pro.style.top="1vw";
	Ipro.style.opacity="1";
	searchs.style.display="none";
	closepro.style.zIndex="99999";
		},800);
	}	
function profiles(){
	this.style.background="#d46060";
	};
var rateing = function() {
			ty.style.opacity="1";
			var att = this.getAttribute("data-myattribute");
			for(var i = 0; i < star.length; i++){
				if(att > i){
				use[i].setAttribute('xlink:href','#icon-star-full');
				}else{
					use[i].setAttribute('xlink:href','#icon-star-empty');
					}
				}
						ty.innerHTML="THANKS FOR RATING " + att + "/5";
};

for(var i=0;i<star.length;i++){
    star[i].addEventListener('click', rateing, false);
}