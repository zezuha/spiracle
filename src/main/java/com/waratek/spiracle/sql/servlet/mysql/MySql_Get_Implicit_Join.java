/*
 *  Copyright 2014 Waratek Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.waratek.spiracle.sql.servlet.mysql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waratek.spiracle.sql.servlet.util.ParameterNullFix;
import com.waratek.spiracle.sql.util.SelectUtil;

/**
 * Servlet implementation class Get_Inner_Join
 */
@WebServlet("/MySql_Get_Implicit_Join")
public class MySql_Get_Implicit_Join extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySql_Get_Implicit_Join() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeRequest(request, response);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {          
        ServletContext application = this.getServletConfig().getServletContext();
        List<String> queryStringList = new ArrayList<String>();     
        queryStringList.add("id");
        
        Map<String, String> nullSanitizedMap = ParameterNullFix.sanitizeNull(queryStringList, request);
        
        String id = nullSanitizedMap.get("id");

        String sql = "SELECT * FROM users, address WHERE users.id = " + id + " AND users.id = address.id";

        Boolean showErrors = true;
        Boolean allResults = true;
        Boolean showOutput = true;

        SelectUtil.executeQuery(sql, application, request, response, showErrors, allResults, showOutput);
    }

}
