(defparameter *nodes*
  '((living-room
     (you are in the living-room. a wizard is snoring loudly on the couch.))
    (garden
      (you are in a beautiful garden. there is a well in front of you.))
    (attic
      (you are in the attic. there is a giant welding torch in the corner.))))

(defun describe-location (location nodes)
  (cadr (assoc location nodes)))

(defparameter *edges*
  '((living-room (garden west door) (attic upstairs ladder))
    (garden (living-room east door))
    (attic (living-room downstairs ladder))))

(defun describe-path (edge)
  `(there is a ,(caddr edge) going ,(cadr edge) from here.))

;; (append '(mary had) '(a) '(little lamb))
;; (apply #'append '((mary had) (a) (little lamb))
;; #'append == (function append)
(defun describe-paths (location edges)
  (apply #'append (mapcar #'describe-path (cdr (assoc location edges)))))

(defparameter *objects* '(whiskey bucket frog chain))

(defparameter *object-locations*
  '((whiskey living-room)
    (bucket living-room)
    (chain garden)
    (frog garden)))

;; in flet, bindings are not recursive and cannot refer to each other.
;; in labels, bindings are recursive and can refer to each other.
(defun objects-at (loc objs obj-locs)
  (flet ((at-loc-p (obj)
           (eq (cadr (assoc obj obj-locs)) loc)))
        (remove-if-not #'at-loc-p objs)))

(defun describe-objects (loc objs obj-loc)
  (flet ((describe-obj (obj)
           `(you see a ,obj on the floor.)))
    (apply #'append
           (mapcar #'describe-obj
		   (objects-at loc objs obj-loc)))))

(defparameter *location* 'living-room)

(defun look ()
  (append (describe-location *location* *nodes*)
          (describe-paths *location* *edges*)
          (describe-objects *location* *objects* *object-locations*)))

;; (find 'y '((5 x) (3 y) (7 z)) :key #'cadr) => (3 y)
(defun walk (direction)
  (let ((next (find direction
                    (cdr (assoc *location* *edges*))
                    :key #'cadr)))
        (if next
            (progn
	      (setf *location* (car next))
	      (look))
	    '(you cannot go that way.))))

(defun pickup (object)
  (cond ((member object (objects-at *location* *objects* *object-locations*))
	 (push (list object 'body) *object-locations*)
         `(you are now carrying the ,object))
        (t '(you cannot get that.))))

(defun inventory ()
  (cons 'items- (objects-at 'body *objects* *object-locations*)))

(defun game-repl ()
  (let ((cmd (game-read)))
       (unless (eq (car cmd) 'quit)
         (game-print (game-eval cmd))
               (game-repl))))

;; read-from-string: parse the printed presentation of a object from subsequence string
(defun game-read ()
  (let ((cmd (read-from-string
              (concatenate 'string "(" (read-line) ")"))))
       (flet ((quote-it (x) (list 'quote x)))
             (cons (car cmd) (mapcar #'quote-it (cdr cmd))))))

(defparameter *allowed-commands* '(look walk pickup inventory))

(defun game-eval (sexp)
  (if (member (car sexp) *allowed-commands*)
      (eval sexp)
      '(i do not know that command.)))

(defun tweak-text (lst caps lit)
  (when lst
    (let ((item (car lst))
          (rest (cdr lst)))
      (cond ((eql item #\space) (cons item (tweak-text rest caps lit)))
            ((member item '(#\! #\? #\.)) (cons item (tweak-text rest t lit)))
            ((eql item #\") (tweak-text rest caps (not lit)))
            (lit (cons item (tweak-text rest nil lit)))
            (caps (cons (char-upcase item) (tweak-text rest nil lit)))
            (t (cons (char-downcase item) (tweak-text rest nil nil)))))))

;; coerce is similar to casting in ohter languages.
;; (coerce xxx 'list) => list (eg. list of characters)
;; (coerce xxx 'string) => string
;;
;; prin1 prints a string and doesn't newline.
;; prin1-to-string has the same execution method as prin1,
;; but instead of standard output, it creates a string.
;;
;; string-trim character-bang string => trimmed-string
;; string-trim returns a substring of string, all characters in character-bang
;; stripped off the beginning and end.
;;
;; (fmakunbound `game-print undefun game-print.
(defun game-print (lst)
  (princ (coerce
	  (tweak-text
	   (coerce (string-trim "() " (prin1-to-string lst)) 'list)
	   t nil)
	  'string))
  (fresh-line))
