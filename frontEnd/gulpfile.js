// 获取gulp
var gulp = require('gulp')

/*处理JS*/
// 获取uglify
var uglify = require('gulp-uglify');
//压缩js
gulp.task('script',function(){
   gulp.src('src/js/*.js')
       .pipe(uglify())
       .pipe(gulp.dest('dist/js'));
});

/*处理CSS*/
// 获取minify-css
var minifyCSS=require('gulp-minify-css');
//压缩css
gulp.task('css',function(){
   gulp.src('src/css/*.css')
       .pipe(minifyCSS())
       .pipe(gulp.dest('dist/css'));
});

/*处理图片*/
// 获取imagemin
var imagemin = require('gulp-imagemin');
gulp.task('images',function () {
   gulp.src('src/images/*.*')
       .pipe(imagemin({
          optimizationLevel: 5, //类型：Number  默认：3  取值范围：0-7（优化等级）
          progressive: true, //类型：Boolean 默认：false 无损压缩jpg图片
          interlaced: true, //类型：Boolean 默认：false 隔行扫描gif进行渲染
          multipass: true //类型：Boolean 默认：false 多次优化svg直到完全优化
       }))
       .pipe(gulp.dest('dist/images'));
})

/*监控js|CSS文件|图片文件*/
gulp.task('auto',function(){
   gulp.watch('src/js/*.js',['script']);
   gulp.watch('src/css/*.css',['css']);
   gulp.watch('src/images/*.*',['images'])
});

//默认任务
gulp.task('default',['script','css','images','auto']);
