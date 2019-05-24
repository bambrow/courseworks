;; No.1
;; (fromTo k n) Returns the list of integers from number k to number n.
;;              The size of the problem is the number of elements between k and n.
;; Base Case: If k is greater than n, then the size of the problem is 0 and empty
;;            list is returned.
;; Hypothesis: Assume that (fromTo (+ k 1) n) works and returns a list from k+1
;;             to n, and now we need to return the list from k to n.
;; Recursive Step: Now we just need to cons the element k to the list from k+1
;;                 to n, so we have: (fromTo k n) = (cons k (fromTo (+ k 1) n))

(define (fromTo k n)
  (cond ((> k n) '())
        (else (cons k (fromTo (+ k 1) n)))))


;; No.2
;; (removeMults m L) Returns a list containing all the elements in L except for
;;                   those elements who are multiples of m.
;; Base Case: If L is empty, then empty list is returned.
;; Hypothesis: Assume that (removeMults m (cdr L)) works and returns a list containing
;;             all the elements of (cdr L) except for those who are multiples of m, and
;;             now we need to return the new list based on current element (car L) and
;;             the list returned by (removeMults m (cdr L)).
;; Recursive Step: Now we check if (car L) is a multiple of m. If it is, then we just
;;                 return (removeMults m (cdr L)); if not, we need to return
;;                 (cons (car L) (removeMults m (cdr L)))

(define (removeMults m L)
  (cond ((null? L) '())
        ((= (modulo (car L) m) 0) (removeMults m (cdr L)))
        (else (cons (car L) (removeMults m (cdr L))))))


;; No.3
;; (removeAllMults L) Returns a list containing those elements of L that are not
;;                    multiples of each other, suppose that the given elements in L
;;                    are strictly increasing.
;; Base Case: If L is empty, then empty list is returned.
;; Hypothesis: Assume that (removeAllMults (cdr L)) works and returns a list containing
;;             elements of (cdr L) that are not multiples of each other, and we need to
;;             use this list to compute the new result.
;; Recursive Step: Now we use (car L) to eliminate the multiples of (car L) in
;;                 (removeAllMults (cdr L)), and cons (car L) to the front of the
;;                 resulting list, and now we have the new list in which there is no
;;                 element that is multiple of other elements. So we should return
;;                 (cons (car L) (removeMults (car L) (removeAllMults (cdr L))))

(define (removeAllMults L)
  (cond ((null? L) '())
        (else (cons (car L) (removeMults (car L) (removeAllMults (cdr L)))))))


;; No.4
;; (primes n) Returns the list of all primes less than or equal to n.
;;           Since prime number starts from 2, so we generate initial list
;;           containing elements from 2 to n using (fromTo 2 n). Now we only need
;;           to use removeAllMults to eliminate any element that might be
;;           multiple of other elements. The remaining elements must all be prime.
;;           So we return (removeAllMults (fromTo 2 n)).

(define (primes n)
  (removeAllMults (fromTo 2 n)))


;; No.5
;; (maxDepth L) Returns the maximum nesting depth of the elements in L, and the top
;;              elements are at depth 0.
;; Base Case: If L is empty, return 0.
;; Hypothesis: Assume that we have the answer of (maxDepth (cdr L)). And we now need to
;;             use this result and (car L) to compute the current maximum depth.
;;             We can compute the max depth of (car L), if (car L) is itself a list.
;; Recursive Step: We check if (car L) is a list. If it is not, then we need only to
;;                 return (maxDepth (cdr L)) because (car L) has no contribution to
;;                 the max depth. If it is a list, then we need to check the following
;;                 to variables and evaluate which one is bigger: (+ (maxDepth (car L)) 1)
;;                 and (maxDepth (cdr L)). If the former is bigger, then the current
;;                 max depth is the former; if the latter is bigger, then the current
;;                 max depth is the latter.

(define (maxDepth L)
  (cond ((null? L) 0)
        ((list? (car L)) (let ((cur (+ (maxDepth (car L)) 1))
                               (next (maxDepth (cdr L))))
                           (cond ((> cur next) cur)
                                 (else next))))
        (else (maxDepth (cdr L)))))

;; No.6
;; (prefix exp) Returns the prefix notation of the given infix arithmatic expression.
;;              The prefix notation has the operator preceding the operands. In this
;;              question, exp can either be an atom or a list containing at least three
;;              elements: 1st operand, operator, 2nd operand. Each operand can itself
;;              be an atom or a list. When the list contains more than 3 elements,
;;              the operators should be considered as right associative and they
;;              all have the same precedence.
;; Base Case: If exp is a number or a symbol, return it directly.
;; Hypothesis: First we need to find the smaller problem. For a list containing only
;;             one element, the smaller problem should be that element; for a list
;;             containing more than one elements (and we know in this case the list
;;             contains at least three elements), we should rearrange those elements.
;;             In this case (cadr exp) is the operator, (car exp) is the first operand
;;             and (cddr exp) is the second operand. We need to compute the result
;;             based on these.
;; Recursive Step: First we check the if exp only contains one element. If (cdr exp)
;;                 is null, we return (prefix (car exp)). If (cdr exp) is not null,
;;                 we know that it has at least three elements, and (cadr exp) is the
;;                 operator, (car exp) is the first operand. We do (prefix (car exp))
;;                 and make the result a list; and then cons the (prefix (cadr exp))
;;                 to this list. Next we need to handle the second operand, which
;;                 is (cddr exp), and the process is the same as processing the first
;;                 operand. The list generated after processing the second operand
;;                 should be (list (prefix (cddr exp))), and we append the first
;;                 list to this list to make it the final result. The final result is
;;                 (append (cons (prefix (cadr exp)) (list (prefix (car exp))))
;;                      (list (prefix (cddr exp)))).

(define (prefix exp)
  (cond ((or (number? exp) (symbol? exp)) exp)
        ((null? (cdr exp)) (prefix (car exp)))
        (else (append (cons (prefix (cadr exp)) (list (prefix (car exp))))
                      (list (prefix (cddr exp)))))))


;; No.7
;; (composition fns) Returns the compoisition function of all the functions in fns. The
;;                   list fns contains at least one function.
;; Base Case: If fns contains only one function, then return it directly.
;; Hypothesis: Assume that we have the answer of (composition (cdr fns)). And we
;;             now need to compute the composition of (car fns) and the above result.
;; Recursive Step: Using lambda function, to return a function that uses (car fns) to
;;                 run upon the result of ((composition (cdr fns)) x). The resulting
;;                 lambda function is exactly the composition of (car fns) and
;;                 (composition (cdr fns)).

(define (composition fns)
  (cond ((null? (cdr fns)) (car fns))
        (else (lambda (x) ((car fns) ((composition (cdr fns)) x))))))


;; No.8
;; (bubble-to-nth L N) Returns a list containing all the elements in L, but the largest
;;                     element of the first N elements of L is placed at the Nth
;;                     position of the new list. The elements after the Nth position
;;                     should be left in their original order.
;; Base Case: If the list is empty, or the list contains only one element, or N ls less
;;            than 2, return the list directly.
;; Hypothesis: Assume that we know the answer of the smallest problem, and we call the
;;             smaller problem is the list M. M is a list one element smaller than L,
;;             but the missing element can be the first or the second element of L,
;;             depending on which one is smaller. Now we know the answer of
;;             (bubble-to-nth M (- N 1)).
;; Recursive Step: We need to construct M. To do this, first we compare the first two
;;                 elements in the list. If (car L) is bigger than (cadr L), we
;;                 cons (car L) to (cddr L) to make this new list the smaller problem
;;                 M and do (bubble-to-nth (cons (car L) (cddr L)) (- N 1)), and then
;;                 cons the (cadr L) to the resulting list. If (car L) is smaller than
;;                 (cadr L), then the smaller problem M is just (cdr L) and we only
;;                 need to cons the (car L) to (bubble-to-nth (cdr L) (- N 1)).

(define (bubble-to-nth L N)
  (cond ((or (null? L) (null? (cdr L)) (< N 2)) L)
        ((> (car L) (cadr L))
         (cons (cadr L) (bubble-to-nth (cons (car L) (cddr L)) (- N 1))))
        (else (cons (car L) (bubble-to-nth (cdr L) (- N 1))))))


;; No.9
;; (b-s L N) Returns a list containing all the elements in L, but the first N elements
;;           are in sorted order. The element after the Nth position are should be
;;           left in their original order.
;; Base Case: If N is less than 2, then return the list directly.
;; Hypothesis: Assume that we know the answer of the smallest problem. In the smaller
;;             problem, the first N-1 elements are sorted, so their relative order is
;;             fixed. If this can be done, to solve the current problem, we first
;;             need to place the biggest element in the Nth position.
;; Recursive Step: Now we first need to place the largest element in the Nth position,
;;                 using (bubble-to-nth L N), and then use this resulting list to do
;;                 the same problem one size smaller using (b-s (bubble-to-nth L N) (- N 1)).

(define (b-s L N)
  (cond ((< N 2) L)
        (else (b-s (bubble-to-nth L N) (- N 1)))))


;; No.10
;; (bubble-sort L) Returns the list of all the elements in L in sorted order, using the
;;                 bubble sort. We already have the function of (b-s L N) that can sort
;;                 the first N elements in L, we only need to specify N as the length
;;                 of L. Therefore, we return (b-s L (length L)).

(define (bubble-sort L)
  (b-s L (length L)))
