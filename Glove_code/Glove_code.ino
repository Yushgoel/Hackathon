const int flex_thumb = A0;
const int flex_index = A1;
const int flex_middle = A2;
const int flex_ring = A3;
const int flex_little = A4;

bool thumb = false;
bool index = false;
bool middle = false;
bool ring = false;
bool little = false;

bool new_sign = false;

const float VCC = 4.98;
const float R_DIV = 3000;


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(flex_thumb, INPUT);
  pinMode(flex_index, INPUT);
  pinMode(flex_middle, INPUT);
  pinMode(flex_ring, INPUT);
  pinMode(flex_little, INPUT);
  new_sign = true;
  
  
}

void loop() {
  // put your main code here, to run repeatedly:

  int flexADC_thumb = analogRead(flex_thumb);
  int flexADC_index = analogRead(flex_index);
  int flexADC_middle = analogRead(flex_middle);
  int flexADC_ring = analogRead(flex_ring);
  int flexADC_little = analogRead(flex_little);
  
  if (flexADC_thumb >= 32.0)
  {
    thumb = true;
  }
  if (flexADC_index >= 32.0)
  {
    index = true;
  }
  if (flexADC_middle >= 32.0)
  {
    middle = true;
  }
  if (flexADC_ring >= 32.0)
  {
    ring = true;
  }
  if (flexADC_little >= 32.0)
  {
    little = true;
  }

  /*if (new_sign)
  {
    // all the signs come here
  }

  else 
  {
    if (!thumb && !index && !middle && !ring && !little)
    {
      new_sign = true;
    }
  }
*/

  Serial.println("thumb " + flexADC_thumb);
  thumb = false;
  index = false;
  middle = false;
  ring = false;
  little = false;

  delay(750);
  
}
