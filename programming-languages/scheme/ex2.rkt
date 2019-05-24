(define (map-list fns L)
  (cond ((null? fns) '())
        (else (cons ((car fns) (car L)) (map-list (cdr fns) (cdr L))))))

(map-list (list (lambda (x) (+ x 1)) (lambda (x) (* x 3))) '(2 5))

((lambda (x y) (+ x y)) 2 6)

(let ((x (+ 3 4)) (y (* 2 7))) (- y x))

(let* ((x 10) (y (+ x 5))) y)

(letrec ((f (lambda (x) (if (= x 0) 1 (* x (f (- x 1))))))) (f 4))

(define (ho-map-list fns)
  (lambda (x)
    (letrec ((f (lambda (fs L)
                  (cond ((null? fs) '())
                        (else (cons ((car fs) (car L)) (f (cdr fs) (cdr L))))))))
      (f fns x))))

(define f (ho-map-list (list (lambda (x) (+ x 1)) (lambda (x) (* x 3)))))
(f '(2 5))

(define (atom? x)
  (if (or (symbol? x) (number? x) (boolean? x)) #t #f))

(map atom? '(1 + () #f))

(define (empty? L)
  (cond ((null? L) #t)
        ((atom? (car L)) #f)
        (else (and (empty? (car L)) (empty? (cdr L))))))

(empty? '(() (() (() ())) () ((() ((() ((() () (())))) ())) ())))

(define (reduce f L)
  (cond ((null? L) 0)
        ((null? (cdr L)) (car L))
        (else (f (car L) (reduce f (cdr L))))))

(reduce (lambda (x y) (if (> x y) x y)) '(3 5 2 1 6 3 2 6 4 8 2))
(reduce max '(3 5 2 1 6 3 2 6 4 8 2))

(apply max '(3 5 2 1 6 3 2 6 4 8 2))
(apply + 1 2 '(3 4 5))
(apply - 100 10 '(9 8 7 6 5 4 3 2 1))

(letrec ((f (lambda (x) (if (= x 0) 1 (* x (f (- x 1))))))
           (g (lambda (x) (if (= x 0) 0 (+ x (g (- x 1))))))
           (h (lambda (x) (if (= x 0) 1 (* (f x) (g x)))))
           ) (h 4))

(letrec ((f (lambda (x) (if (= x 0) 1 (* x (f (- x 1))))))
           (g (lambda (x) (if (= x 0) 1 (* (f x) (f x)))))
           (h (lambda (x) (if (= x 0) 1 (* (g x) (g x)))))
           ) (h 10))

(define (compose f g)
    (lambda (x) (f (g x))))

(define (addk k)
    (lambda (x) (+ k x)))

((compose (addk 2) (addk 3)) 0)

(define (greater f g)
    (lambda (x)
      (let ((fx (f x)) (gx (g x)))
        (if (> fx gx) fx gx))))

((greater (addk 2) (addk 3)) 0)

(define (compose-list fns)
    (cond ((null? fns) (lambda (x) x))
          (else (lambda (x) ((car fns) ((compose-list (cdr fns)) x))))))

((compose-list '()) 0)
((compose-list (list (addk 1) (addk 2) (addk 3) (addk 4))) 0)


