Reid Meyer
CSE 438 Mobile Lab 1
Calorie Counter

Works as expected.

Spent most time on ListView, alertDialog, filtering content, layout design.

I tried to deal with many edge cases. One for example, was that if you overflow the calorie input fields. I limited calorie inputs to smaller than like 8 digits, since it is highly unlikely that any one item contains that many calories. 

Creative Portion:


1. I added a reset button. I thought this would be useful. If you're on a new day, you can reset everything and enter in a new calorie input. This erases the array list, resets all of the variables, and reloads the page after a new calorie total is provided. 

2. I added a cool transition animation when you click the add food and add workout button. It's a slow fade. I can edit the duration and whatnot. It occurs when you enter the activity and leave. I thought it would be fun to play with transition animations. 

3. Later I would want to implement a smooth user interface where one can hold a finger onto an item to have the option to delete it. However, at this state, we don't know very much, so I thought it would be helpful to have a "delete last item" button, in case the user added an item in error. 

4. Lastly, I implemented another activity similar to add food, called add workout. This is so the user can keep track of things they did to counter act calorie intake. Thought it would be easier than it was. For about an hour, I was debugging, only to discover that for some reason my activity file wasn't declared in my manifest! Finally got it to work!

