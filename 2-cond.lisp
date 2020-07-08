(defvar *number-is-odd* nil)
(defun pudding-eater (person)
  (cond ((eq person 'henry) (setf *arch-enemy* 'stupid-lisp-alien)
                            '(curse you lisp alien ? you ate my pudding))
        ((eq person 'johnny) (setf *arch-enemy* 'useless-old-johnny)
                            '(i hope you chocked on my pudding johnny))
        (t                  '(why you eat my pudding stranger ?))))

(defun pudding-eater2 (person)
  (case person
    ((henry) (setf *arch-enemy* 'stupid-lisp-alien)
        '(curse you lisp alien ? you ate my pudding))
    ((johnny) (setf *arch-enemy* 'useless-old-johnny)
        '(i hope you chocked on my pudding johnny))
    (otherwise
        '(why you eat my pudding stranger ?))))
