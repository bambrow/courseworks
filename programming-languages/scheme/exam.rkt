(define (contains x L)
  (cond ((null? L) #f)
        ((equal? x (car L)) #t)
        (else (contains x (cdr L)))))


(define (app-no-dup L1 L2)
  (cond ((null? L1) L2)
        ((null? L2) L1)
        ((contains (car L1) L2) (app-no-dup (cdr L1) L2))
        (else (cons (car L1) (app-no-dup (cdr L1) L2)))))