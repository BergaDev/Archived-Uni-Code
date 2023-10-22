/* Matthew Bergamini 8137225 */
USE csit115;

/* Question 1 */
SELECT E.FNAME, E.LNAME
FROM EMPLOYEE E
INNER JOIN DRIVER D ON E.ENUM = D.ENUM
WHERE D.STATUS = 'ON LEAVE';


/* Question 2 */
SELECT DISTINCT T.REGNUM
FROM TRUCK T
INNER JOIN TRIP TR ON T.REGNUM = TR.REGNUM
WHERE TR.TDATE >= '2016-01-01' AND TR.TDATE <= '2016-12-31'
AND T.STATUS = 'USED';


/* Question 3 NOT WORKING* FNAME, LNAME drive at least one trup in Jan 2016, standard date extract function */
/*
SELECT DISTINCT E.FNAME, E.LNAME
FROM DRIVER D, EMPLOYEE E
JOIN TRIP T ON D.LNUM = T.LNUM
WHERE E.FNAME IS NOT NULL;
WHERE MONTH(T.TDATE) = 1 AND YEAR(T.TDATE) = 2016; */






/* Question 4 */
SELECT E.FNAME, E.LNAME
FROM EMPLOYEE E
LEFT JOIN DRIVER D ON E.ENUM = D.ENUM
WHERE D.ENUM IS NULL;


/* Question 5 */
SELECT E.FNAME, E.LNAME
FROM EMPLOYEE E
LEFT JOIN DRIVER D ON E.ENUM = D.ENUM
LEFT JOIN TRIP T ON D.LNUM = T.LNUM
WHERE T.TNUM IS NULL;


/* Question 6 */
SELECT D.LNUM AS "Driver License Number", COUNT(T.TNUM) AS "Total Trips"
FROM DRIVER D
LEFT JOIN TRIP T ON D.LNUM = T.LNUM
GROUP BY D.LNUM;

