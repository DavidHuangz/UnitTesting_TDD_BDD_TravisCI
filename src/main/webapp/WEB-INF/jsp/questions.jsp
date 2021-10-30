<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Meeting Questions</title>
</head>

<body>
	<h1>Meeting Questions</h1>
	<form method="post">
		<h2>Live Meeting</h2>
		Ask question : <input type="text" name="askQuestion" id="askQuestion" />
		<input type="submit" id="askBtn" value="Ask"/>
               <BR/><font id ="errorMessage" color="red">${errorMessage}</font>
               <font id ="errorMessage2" color="red">${errorMessage2}</font><BR/>

		Answer question: <input type="text" name="answerQuestion" id="answerQuestion"/>
        <select name="questionSelection" size="1">
        <option value="">Select question</option>
            <c:forEach items="${List}" var="question" varStatus="i">
                <option id="${i.index+1}" value=${i.index+1}>${i.index+1}</option>
            </c:forEach>
        </select>
            <input type="submit" id="answerBtn" value="Answer">

               <BR/><BR/>

                        Upvote question:
                        <select name="upvoteSelection" id="upvoteSelection" size="1" onchange="this.form.submit()">
                             <option value="">Upvote</option>
                                   <c:forEach items="${List}" var="question" varStatus="i">
                                <option id="${i.index+1}" value=${i.index+1}>${i.index+1}</option>
                            </c:forEach>
                        </select>

                             <BR/><BR/>

                                Delete question:
                             <select name="deleteSelection" id="deleteSelection" size="1" onchange="this.form.submit()">
                                     <option value="">Delete</option>
                                        <c:forEach items="${List}" var="question" varStatus="i">
                                        <option id="${i.index+1}" value=${i.index+1}>${i.index+1}</option>
                                        </c:forEach>
                                      </select>

                                           <BR/><BR/>

                                          Mark Question:
                                           <select name="MarkSelection" id="MarkSelection" size="1" onchange="this.form.submit()">
                                           <option value="">Resolve</option>
                                            <c:forEach items="${List}" var="question" varStatus="i">
                                            <option id="${i.index+1}" value=${i.index+1}>${i.index+1}</option>
                                            </c:forEach>
                                          </select>

                                           <BR/><BR/>

                                             <select name="sortSelection" id="sortSelection" size="1" onchange="this.form.submit()">
                                                    <option value="">Sort by</option>
                                                    <option value="Latest">Latest</option>
                                                    <option value="upvote">upvote</option>
                                                     <option value="Answered Questions">Answered</option>
                                                 </select>

                                     <c:choose>
                                              <c:when
                                              test="${param.sortSelection=='upvote'}">
                                             <font id ="questionListUpvote" color="purple">
                                                 <BR/><h3>Most Voted Questions</h3>
                                                   <c:forEach items="${List}" var="question" varStatus="i">
                                                        ${qh.getQuestionUpvoteList().get(i.index).getQuestions()}
                                                        <BR/>
                                                        Resolved: ${qh.getQuestionUpvoteList().get(i.index).getResolved()}
                                                        <BR/>
                                                        Vote: ${qh.getQuestionUpvoteList().get(i.index).getVote()}
                                                        <BR/>
                                                        Reply: ${qh.getQuestionUpvoteList().get(i.index).getAnswer()}
                                                        <BR/><BR/>
                                                    </c:forEach>
                                                    </font>
                                             </c:when>

                                                    <c:when
                                                         test="${param.sortSelection=='Answered Questions'}">
                                                           <font id ="questionListAnswered" color="green">
                                                           <BR/><h3>Answered Questions</h3>
                                                           <c:forEach items="${AnsweredList}" var="question" varStatus="i">
                                                           ${qh.getAnsweredQuestionsList().get(i.index).getQuestions()}
                                                           <BR/>
                                                           Resolved: ${qh.getAnsweredQuestionsList().get(i.index).getResolved()}
                                                          <BR/>
                                                          Vote: ${qh.getAnsweredQuestionsList().get(i.index).getVote()}
                                                           <BR/>
                                                          Reply: ${qh.getAnsweredQuestionsList().get(i.index).getAnswer()}
                                                        <BR/><BR/>
                                                        </c:forEach>
                                                       </font>
                                                     </c:when>

                                                  <c:otherwise>
                                                   <font id ="questionListTime" color="blue">
                                                      <BR/><h3>Latest Questions</h3>
                                                             <c:forEach items="${List}" var="question" varStatus="i">
                                                                ${i.index+1}. ${qh.getList().get(i.index).getQuestions()}
                                                                <BR/>
                                                                Resolved: ${qh.getList().get(i.index).getResolved()}
                                                                <BR/>
                                                                Vote: ${qh.getList().get(i.index).getVote()}
                                                                  <BR/>
                                                                 Reply: ${qh.getList().get(i.index).getAnswer()}
                                                                 <BR/><BR/>
                                                              </c:forEach>
                                                             </font>
                                                    </c:otherwise>
                                             </c:choose>
	</form>
</body>
</html>