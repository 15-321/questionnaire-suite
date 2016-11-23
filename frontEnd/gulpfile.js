var gulp = require('gulp');
var gutil = require('gulp-util');
var combiner = require('stream-combiner2');
var watchPath = require('gulp-watch-path');
var autoprefixer = require('gulp-autoprefixer');
var handleError = function (err) {
    var color = gutil.colors;
    console.log('\n');
    gutil.log(color.red('Error!'));
    gutil.log('fileName: ' + color.red(err.fileName));
    gutil.log('lineNumber ' + color.red(err.lineNumber));
    gutil.log('message ' + err.message);
    gutil.log('plugin ' + color.yellow(err.plugin));
};


/*处理JS*/
// 获取uglify
var uglify = require('gulp-uglify');
// 编译所有的js
gulp.task('uglifyjs', function () {
    var combined = combiner.obj([
        gulp.src('src/js/**/*.js'),
        uglify(),
        gulp.dest('dist/js/')
    ])
    combined.on('error', handleError)
})
// 监测js文件目录，对变动的js进行压缩
gulp.task('watchjs', function () {
    gulp.watch('src/js/**/*.js', function (event) {
        var paths = watchPath(event, 'src/', 'dist/');
        //打印
        gutil.log(gutil.colors.green(event.type) + ' ' + paths.srcPath);
        gutil.log('Dist: ' + paths.distPath);
        //执行
        var combined = combiner.obj([
            gulp.src(paths.srcPath),
            uglify(),
            gulp.dest(paths.distDir)
        ])
        combined.on('error', handleError);
    })
});


/*处理CSS*/
// 获取minify-css
var minifyCSS = require('gulp-minify-css');
//压缩所有的css文件
gulp.task('minifyCSS', function () {
    gulp.src('src/css/**/*.css')
        .pipe(autoprefixer({
            browsers: 'last 2 versions'
        }))
        .pipe(minifyCSS())
        .pipe(gulp.dest('dist/css/'))
});
//监测css文件目录，对变动的css进行压缩
gulp.task('watchcss', function () {
    gulp.watch('src/css/**/*.css', function (event) {
        var paths = watchPath(event, 'src/', 'dist/');
        //打印
        gutil.log(gutil.colors.green(event.type) + ' ' + paths.srcPath);
        gutil.log('Dist: ' + paths.distPath);
        //执行
        gulp.src(paths.srcPath)
            .pipe(autoprefixer({
                browsers: 'last 2 versions'
            }))
            .pipe(minifyCSS())
            .pipe(gulp.dest(paths.distDir));
    })
});

/*复制HTML页面*/
gulp.task('copyhtml',function(){
    gulp.src('src/*.html')
        .pipe(gulp.dest('dist'));
});
//监测html文件目录，对变动的html进行复制
gulp.task('watchhtml', function () {
    gulp.watch('src/*.html', function (event) {
        var paths = watchPath(event, 'src/', 'dist/');
        //打印
        gutil.log(gutil.colors.green(event.type) + ' ' + paths.srcPath);
        gutil.log('Dist: ' + paths.distPath);
        //执行
        gulp.src(paths.srcPath)
            .pipe(gulp.dest(paths.distDir));
    })
});

/*处理图片*/
// 获取imagemin
var imagemin = require('gulp-imagemin');
// 压缩所有的images
gulp.task('minimages', function () {
    gulp.src('src/images/*.*')
        .pipe(imagemin({
            optimizationLevel: 5, //类型：Number  默认：3  取值范围：0-7（优化等级）
            progressive: true, //类型：Boolean 默认：false 无损压缩jpg图片
            interlaced: true, //类型：Boolean 默认：false 隔行扫描gif进行渲染
            multipass: true //类型：Boolean 默认：false 多次优化svg直到完全优化
        }))
        .pipe(gulp.dest('dist/images'));
})
// 监测变动的images图片进行压缩
gulp.task('watchimages', function () {
    gulp.watch('src/images/**/*.*', function (event) {
        var paths = watchPath(event, 'src/', 'dist/');
        //打印
        gutil.log(gutil.colors.green(event.type) + ' ' + paths.srcPath);
        gutil.log('Dist: ' + paths.distPath);
        //执行
        gulp.src(paths.srcPath)
            .pipe(imagemin({
                optimizationLevel: 5, //类型：Number  默认：3  取值范围：0-7（优化等级）
                progressive: true, //类型：Boolean 默认：false 无损压缩jpg图片
                interlaced: true, //类型：Boolean 默认：false 隔行扫描gif进行渲染
                multipass: true //类型：Boolean 默认：false 多次优化svg直到完全优化
            }))
            .pipe(gulp.dest(paths.distDir));
    })
});


//默认任务
gulp.task('default', ['watchjs', 'watchcss', 'watchimages','watchhtml']);

//TODO 查看autoprefixer用法以及watchPath函数参数