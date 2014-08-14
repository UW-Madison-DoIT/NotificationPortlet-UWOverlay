<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<c:set var="n"><portlet:namespace/></c:set>

<portlet:actionURL var="invokeNotificationServiceUrl" escapeXml="false">
    <portlet:param name="uuid" value="${uuid}"/>
    <portlet:param name="action" value="invokeNotificationService"/>
</portlet:actionURL>

<c:if test="${!usePortalJsLibs}">
    <script src="<rs:resourceURL value="/rs/jquery/1.6.1/jquery-1.6.1.min.js"/>" type="text/javascript"></script>
</c:if>
<script src="<c:url value="/scripts/jquery.notice.min.js"/>" type="text/javascript"></script>

<link rel="stylesheet" href="<rs:resourceURL value="/rs/fontawesome/4.0.3/css/font-awesome.min.css"/>" type="text/css" media="screen" />

<style>
#${n}notificationIcon {
    display: inline-block;
}
#${n}notificationIcon i {
    font-size: ${size}px;
}
#${n}notificationIcon .notification-badge {
    background-color: #f7f5e8;
    border-radius: 4px;
    font-size:1.1em;
    font-weight:200;
    color: #b70101;
    padding:4px 6px 4px 5px;
    margin:12px 20px 12px 8px;
    position: relative;
    text-align: center;
    text-shadow: 0 1px 0 rgba(0,0,0,0.1);
    top:0px;
    line-height:1em;
    border:1px solid #b70101;
}
#${n}notificationIcon .no-new {
    background-color:#b70101;
    color:#f7f5e8;
    border:1px solid #f7f5e8;
}
span.notification-count {
  padding:0px;
  margin:0px;
}
</style>

<div id="${n}notificationIcon">
    <a href="${url}" title="<spring:message code="view.notifications"/>">

    </a>
    <div class="notification-badge" style="display: none;"><span class="notification-count"></span></div>
</div>

<script type="text/javascript">

    var ${n} = ${n} || {};
    <c:choose>
        <c:when test="${!usePortalJsLibs}">
            ${n}.jQuery = jQuery.noConflict(true);
        </c:when>
        <c:otherwise>
            <c:set var="ns"><c:if test="${ not empty portalJsNamespace }">${ portalJsNamespace }.</c:if></c:set>
            ${n}.jQuery = ${ ns }jQuery;
        </c:otherwise>
    </c:choose>

    ${n}.jQuery(function(){
        var $ = ${n}.jQuery;
        var count = upnotice.pullFeed($, {
            invokeNotificationServiceUrl: '${invokeNotificationServiceUrl}',
            getNotificationsUrl: '<portlet:resourceURL id="GET-NOTIFICATIONS-UNCATEGORIZED"/>'
        }, function(feed) {
            if (feed && feed.length > 0) {
                $('#${n}notificationIcon .notification-count').html(feed.length);
                $('#${n}notificationIcon .notification-badge').slideDown();
            } else if (feed && feed.length == 0) {
                $('#${n}notificationIcon .notification-count').html(feed.length);
                $('span.notification-count').addClass('no-new');
            }
        });

    });

</script>
