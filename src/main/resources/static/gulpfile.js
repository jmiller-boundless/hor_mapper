var gulp = require('gulp');
var connect = require('gulp-connect');
var proxy = require('http-proxy-middleware');

gulp.task('connect', function() {
  connect.server({
    root: '.',
    livereload: true,
    middleware: function (connect, opt) {
      return [
        proxy('/grantmapper-0.1', {
          target: 'http://ec2-54-164-142-147.compute-1.amazonaws.com',
          chargeOrigin: true
        })
      ];
    }
  });
});

gulp.task('html', function () {
  gulp.src('./*.html')
    .pipe(connect.reload());
});

gulp.task('js', function () {
  gulp.src('./app/*.js')
    .pipe(connect.reload());
});

gulp.task('css', function () {
  gulp.src('./styles/*.css')
    .pipe(connect.reload());
});

gulp.task('watch', function () {
  gulp.watch(['./*.html'], ['html']);
  gulp.watch(['./styles/*.css'], ['css']);
  gulp.watch(['./app/*.js'], ['js']);
});

gulp.task('default', ['connect', 'watch']);