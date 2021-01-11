/**
 * 
 * canvas画海报工具
 * 
 * 示例
 * var tct = new TB_CanvasTools({
            	
            	//包裹图片的jq对象
            	"pcontent":$("#hb"),
            	//背景颜色
            	"backgroundColor":"#fff",
            	//渲染完成回调事件
            	"callback":function(params){
            		
            		var imageBase64 = params.imageBase64;console.log(imageBase64);
            		$("#hbImage").attr("src",imageBase64);
            	}
            	
            });
            
            //要画的元素集
            var views = [{
            	//类型 image/text
                type: 'image',
                //如果是图片的话，图片地址
                url: '${ctxStatic}/newcontract4.0/images/dianxin_logo_icon.png',
                //css，这里的值都是比例
                css: {
                    top: 0.05,
                    left: 0.05,
                    width: 0.3
                },
                list: [{
                    type: 'image',
                    url: '${ctxStatic}/newcontract4.0/images/haibao.png',
                    css: {
                        top: 0.15,
                        left: 0.05,
                        width: 0.9
                    }
                },
                {
                     type: 'text',
                     text: `今天你消费 我买单！`,
                     css: {
                         color: '#FF9601',
                         fontSize: 20,
                         top: 0.8,
                         left: 0.05
                     }
                 }]
            }]
            
            tct.drawCanvas({
            	
            	"views":views
            	
            });
 * 
 */
var TB_CanvasTools = function(params){
	
	var $this = this;
	
	if(!params){
		params = {};
	}
	
	//包裹图片的元素jq对象
	var pcontent = params.pcontent;
	var backgroundColor = params.backgroundColor;
    var callback = params.callback;
    var globalAlpha = params.globalAlpha;
    var width = params.width;
    var height = params.height;
    
    if(!globalAlpha){
    	globalAlpha = 1;
    }
	
	$this.pcontent = pcontent;
	$this.backgroundColor = backgroundColor;
	$this.callback = callback;
	$this.globalAlpha = globalAlpha;
	
	//canvas 的宽和高
	$this.width = width;
	$this.height = height;
	if($this.width == 0 && $this.height == 0){
		
		$this.width = 400;
		$this.height = 400;
		
	}
	
	$this.width = ($this.width == 0?$this.height:$this.width);
	$this.height = ($this.height == 0?$this.width:$this.height);
	
	$this.tWidth = $this.width;
	$this.tHeight = $this.height;
	//要绘制的内容数
	$this.viewsSize = 0;
	
	//创建canvas对象
	var canvas=document.createElement("canvas");
	canvas.width=$this.width;
	canvas.height=$this.height;
	
	var ctx = canvas.getContext("2d");
	$this.ctx = ctx;
	$this.canvas = canvas;
	

	$this.ctx.globalAlpha = globalAlpha;
	$this.ctx.rect(0, 0, $this.width, $this.height);
    //$this.ctx.fillStyle = $this.backgroundColor;
    $this.ctx.fill();
	$this.ctx.globalAlpha = 1;
	
    //进行绘制的方法
    $this.drawCanvas = function(params){
    	
      $this.views = params.views;	
      
      if(!params){
    	  params = {}
      }
    
      $this.viewsSize = 0;
      
      $this.views.forEach(cur => {
    	initViewSize(cur);
      })
      console.log("viewSize:"+$this.viewsSize);
      $this.views.forEach(cur => {
        drawView(cur)
      })
    }
    
    //绘制图片
    var _drawImage = function(view){
    	
    	var viewSize = $this.viewsSize;
		 if(!view.url){
		   return ;
		 }
		 let {
		   top,left,width,height,maxHeight,pHeight
		 } = view.css;
		 
		 var image = view.image;
		 var iw = image.width;
		 var ih = image.height;
		 console.log("img:"+viewSize+",iw:"+iw+",ih:"+ih);
		 console.log(view.image);
		 //计算宽高
		 width = getWidth(width);
		 //height = getHeight(height);
		 console.log("img:"+viewSize+",width/iw:"+(width/iw).toFixed(2)*ih);
		 height = (width/iw).toFixed(2)*ih;
		 top = getHeight(top);
		 left = getWidth(left);
		 
		 width = Math.round(width);
		 height = Math.round(height);
		 
		 if(maxHeight){
			 
			 var afterMaxHeight = getHeight(maxHeight);
			 if(afterMaxHeight < height){

				 height = afterMaxHeight;
			 }
			 
		 }
		 if(pHeight){
			 height = getHeight(pHeight);
		 }
		 
		 if (typeof view.url === 'string') {
		   $this.ctx.fillStyle = '#ddd'
		   $this.ctx.fillRect(left, top, width, height);
		   $this.ctx.restore()
		   return
		 }
		 $this.ctx.save();
		 //$this.fillStyle = 'rgba(255, 255, 255, 0)';
		 $this.ctx.drawImage(view.url, left, top, width, height);
		 $this.ctx.restore();
		 
		 //绘制记录数减一
		 $this.viewsSize--;console.log("img:"+viewSize+",width:"+width+",height:"+height);
		 if($this.viewsSize == 0){
			 //如果记录数减为0则回调
		 	  finish();
		 }
		 
		 //绘制子
		 if (view.list && view.list.length > 0) {
		   view.list.forEach(cur => drawView(cur))
		 }
    }
    
    /**
     * 绘制文字 
     **/
    var _drawText = function(view, ctx) {
    	
      let css = {
        color: '#333333',
        lineHeight: 30,
        textAlign: 'start',
        maxLines: 3,
        fontSize: 20,
        top: 0,
        left: 0,
        width: 1,
        textBaseline: 'alphabetic'
      }
      
      css = { ...css, ...view.css };

      //设置宽
      css.width = getWidth(css.width);
	  css.top = getHeight(css.top);
	  css.left = getWidth(css.left);
      
      $this.ctx.save();
      $this.ctx.textAlign  = css.textAlign;
      $this.ctx.textBaseline = css.textBaseline;
      $this.ctx.fillStyle = css.color;
      $this.ctx.font = css.fontSize + 'px  Arial';
      let result = breakLinesForCanvas(view.text, css.width, ctx);
      for (let i = 0; i < result.length; i++) {
    	  
        if (i <= css.maxLines - 1) {
          let str = result[i];
          if ((i === css.maxLines - 1) && ($this.ctx.measureText(str+"...").width > css.width)) {
            result[i] = str.substring(0, str.length - 1)+"...";
          }
        } else {
          break;
        }
        $this.ctx.fillText(result[i], css.left, css.top + i * css.lineHeight, css.width);
      }
      $this.ctx.restore();

      $this.viewsSize--;console.log("text:"+$this.viewsSize);
      if($this.viewsSize == 0){
    	  finish();
      }
      if(view.list && view.list.length > 0){
        view.list.forEach(cur => drawView(cur))
      }
    };
    
	/**
	 * 
	 * ==================================function====================================
	 * 
	 */
	/**
	 * 获取内容的宽
	 * 
	 * @params widthScale 宽的比例
	 */
	var getWidth = function(widthScale){
		
		return widthScale*$this.tWidth;
		
	}
	
	/**
	 * 获取内容的高
	 * 
	 * @params heightScale 高的比例
	 */
	var getHeight = function(heightScale){
		
		return heightScale*$this.tHeight;
		
	}
	
	/**
     * 打断文本，返回给canvas绘制
     * */
    var breakLinesForCanvas = function(text, width, ctx) {
    	
      let result = [];
      let breakPoint = 0;
      while ((breakPoint = findBreakPoint(text, width, ctx)) !== -1) {
        result.push(text.substr(0, breakPoint));
        text = text.substr(breakPoint);
      }

      if (text) {
        result.push(text);
      }
      return result;
    }
    
    /**
     * 获取文本换行断点
     * */
    var findBreakPoint = function(text, width, ctx) {
      var min = 0;
      var max = text.length - 1;
      while (min <= max) {
        var middle = Math.floor((min + max) / 2);
        var middleWidth = $this.ctx.measureText(text.substr(0, middle)).width;
        var oneCharWiderThanMiddleWidth = $this.ctx.measureText(text.substr(0, middle + 1)).width;
        if (middleWidth <= width && oneCharWiderThanMiddleWidth > width) {
          return middle;
        }
        if (middleWidth < width) {
          min = middle + 1;
        } else {
          max = middle - 1;
        }
      }
      return -1;
    }
    
    /**
     * 绘制
     */
    var drawView = function(cur){
    	
    	var $this = this;
    	
      switch (cur.type) {
        case 'image':
          if (cur.url) {
            downloadImage(cur.url).then(image => {
              cur.url = image;
              cur.image = image;
              _drawImage(cur);
            });
          }
          break;
        case 'text':
        	_drawText(cur, this.ctx)
          break;
        default:
          break;
      }
    }
    
   /**
    * 绘制完成后
    */
    var finish = function(){
    	
    	var imageBase64 = $this.canvas.toDataURL('image/png')
    	//回调
    	if($this.callback){
    		$this.callback({
    			
    			"imageBase64":imageBase64
    			
    		});
    	}
    }
    
    /**
     * 
     * 初始化内容元素数量
     * 
     */
    var initViewSize = function(view){

        $this.viewsSize = $this.viewsSize+1;
    	
    	if(view.list){

    		view.list.forEach(cur => initViewSize(cur))
    		
    	}
    }
    
    var downloadImage = function(src){
        return new Promise((resolve, reject) => {
          if (src.startsWith('#')) {
            resolve(src)
            return
          }
          const img = new Image()
          //img.onload = () => resolve(img)
          img.onerror = () => reject(`下载图片失败`+src)
          img.crossOrigin = 'anonymous'
          img.src = src
          var timer = setInterval(function() {
        	  if (img.complete) {
        		  resolve(img)
        		  clearInterval(timer)
        	  	}
        	  }, 50)
          
        })
      }
}
