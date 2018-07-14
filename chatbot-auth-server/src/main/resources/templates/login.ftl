<html>
<head>
    <link rel="stylesheet" href="/uaa/css/bootstrap.css">
</head>
<body>
<div class="container">
    <form role="form" action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username"/>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password"/>
        </div>
        <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="/uaa/js/jquery-3.3.1.min.js"></script>
<script src="/uaa/js/bootstrap.js"></script>
</body>
</html>