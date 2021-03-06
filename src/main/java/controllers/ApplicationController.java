/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;


@Singleton
public class ApplicationController {

    public Result index() {
        return Results.html();
    }

    public Result acesUp() {
        return Results.html().template("views/AcesUp/AcesUp.flt.html");
    }
    
    public Result gameGet(){
        Game g = new Game();

        g.buildDeck();
        g.shuffle();

        return Results.json().render(g);
    }

    public Result hitPost(Context context,  @PathParam("theCol") int theCol, Game g) {

        g.errorCode = g.theUser.hit(theCol);
        g.theDealer.autoplay(g.theUser);
        g.judge();
        g.reload();

        return Results.json().render(g);
    }

    public Result stayPost(Context context,  @PathParam("theCol") int theCol, Game g) {
        g.errorCode = g.theUser.stay(theCol);
        g.theDealer.autoplay(g.theUser);
        g.judge();
        g.reload();
        return Results.json().render(g);
    }

    public Result ddownPost(Context context,  @PathParam("theCol") int theCol, Game g) {
        g.errorCode=g.theUser.doubleDown(theCol);
        g.theDealer.autoplay(g.theUser);
        g.judge();
        g.reload();
        return Results.json().render(g);
    }

    public Result splitPost(Context context, Game g) {
        g.errorCode = g.theUser.split();
        g.theDealer.autoplay(g.theUser);
        g.judge();
        g.reload();
        return Results.json().render(g);
    }

    public Result newgamePost(Context context, Game g) {
        g.newGame();
        g.reload();
        return Results.json().render(g);
    }


    public Result removeCard(Context context, @PathParam("column") int colNumber, Game g){

        return  Results.json().render(g);
    }

    public Result moveCard(Context context, @PathParam("columnFrom") int colFrom, @PathParam("columnTo") int colTo, Game g){

        return  Results.json().render(g);
    }

}
