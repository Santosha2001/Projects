<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>

        <!-- <link rel="stylesheet" href="custom.css"> -->
        <!-- <script src="ajax.js"></script> -->

        <style>
            /* form outer border */
            .border {
                width: 35vw;
            }

            /* form input */
            .form-control {
                width: 30vw;
                border-color: rgb(35, 34, 34);
            }

            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
        </style>

    </head>

    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-3 sticky-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Vendor</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Registration.jsp">Register here</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Log In</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Dropdown
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <span style="justify-content: center; text-align: center; color: green;">${otpMatched}</span>

        <span style="justify-content: center; text-align: center; color: red;">${otpNotMatched}</span>


        <div class="container d-flex justify-content-center p-3 border  border-dark mt-2 mb-3">
            <form action="${pageContext.request.contextPath}/otp/otpVerify?vendorEmail=${vendorEmail}" method="get">
                <!-- action="${pageContext.request.contextPath}/otp/otpVerify?otp=${otp}&email=${email}" -->

                <h2 style="text-align: center; color: #29b03e; font-style: italic;">LOG IN</h2>

                <!-- EMAIL ADDRESS FOR LOG IN-->
                <div class="form-group mt-5">
                    <input type="email" class="form-control" id="vendorEmail" name="vendorEmail"
                        placeholder="Email address" value="${mail}" onblur="loginMail()" readonly />
                </div>
                <span id="emailError" style="color: red;"></span>

                <!-- VERIFY OTP -->
                <div class="form-group mt-3">
                    <input type="number" class="form-control" id="otp" name="otp" placeholder="Enter OTP" value=""
                        onchange="otpVerify()" />
                </div>
                <span id="otpError" style="color: red;"></span>


                <button type="submit" class="btn form-control btn-secondary btn-sm mt-4 mb-2" id="otpVerifyBtn">Log
                    In</button>


            </form>
        </div>

        <!-- footer -->
        <footer class="bg-dark py-1 mt-5 footer-1 fixed-bottom">
            <div class="container text-light text-center">
                <p class="display-5 mb-3">Vendor Management</p>
                <small class="text-white-50">&copy; Copyright by X-Workz. All
                    rights reserved.</small>
            </div>
        </footer>

        <!-- <script>
            function otpVerify() {
                const otp = document.getElementById("otp").value;
                const logInBtn = document.getElementById("otpVerifyBtn");

                if (otp != null && otp != "" && otp.length == 6) {
                    document.getElementById("otpError").innerHTML = "";

                    const xhtp = new XMLHttpRequest();
                    xhtp.open("GET", "http://localhost:8080/vmanage/otpVerifyAjax/" + otp);

                    xhtp.send();

                    xhtp.onload = function () {
                        document.getElementById("otpError").innerHTML = this.responseText;
                    }

                    logInBtn.removeAttribute('disabled');

                } else if (otp == null || otp == "" || otp.includes('  ')) {
                    document.getElementById("otpError").innerHTML = "*otp is in-valid.";
                    logInBtn.setAttribute("disabled", "");
                } else if (otp.length != 6) {
                    document.getElementById("otpError").innerHTML = "*otp length is mismatch.";
                    logInBtn.setAttribute("disabled", "");
                }
            }
        </script> -->
    </body>

    </html>