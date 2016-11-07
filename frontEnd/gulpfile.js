// 获取gulp
var gulp = require('gulp')
// 获取uglify
var uglify = require('gulp-uglify');
gulp.task('script',function(){
   // 找到文件
   gulp.src('src/js/*.js')
       .pipe(uglify())
       .pipe(gulp.dest('dist/js'));
});