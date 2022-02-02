package rectangles;

import java.lang.Math;
import java.util.Optional;

public class Rectangle {

  private final int x1;
  private final int x2;
  private final int y1;
  private final int y2;

  public Rectangle(Point leftCorner, int width, int height) {
    this.x1 = leftCorner.getX();
    this.x2 = this.x1 + width;
    this.y1 = leftCorner.getY();
    this.y2 = this.y1 + height;
  }

  public Rectangle(Point first, Point second) {
    this(new Point(Math.min(first.getX(), second.getX()), Math.min(first.getY(), second.getY())),
        Math.abs(second.getX() - first.getX()),
        Math.abs(second.getY() - first.getY())
    );
  }

  public Rectangle(int width, int height) {
    this(new Point(0, 0), width, height);
  }

  //   methods
  public int getWidth() {
    return this.x2 - this.x1;
  }

  public int getHeight() {
    return this.y2 - this.y1;
  }

  public Point getTopLeft() {
    return new Point(this.x1, this.y1);
  }

  public Point getTopRight() {
    return new Point(this.x2, this.y1);
  }

  public Point getBottomLeft() {
    return new Point(this.x1, this.y2);
  }

  public Point getBottomRight() {
    return new Point(this.x2, this.y2);
  }

  public Rectangle setWidth(int newWidth) {
    return new Rectangle(new Point(this.x1, this.y1), newWidth, this.getHeight());
  }

  public Rectangle setHeight(int newHeight) {
    return new Rectangle(new Point(this.x1, this.y1), this.getWidth(), newHeight);
  }

  public int area() {
    return this.getHeight() * this.getWidth();
  }

  public boolean intersects(Rectangle other) {
    return ((other.x1 >= this.x1 && other.x1 <= this.x2)
        || (other.x2 >= this.x1 && other.x2 <= this.x2))
        &&
        ((other.y1 >= this.y1 && other.y1 <= this.y2)
            || (other.y2 >= this.y1 && other.y2 <= this.y2));

  }

  public Optional<Rectangle> intersection(Rectangle other) {
    if (this.intersects(other)) {
      int x1;
      int y1;
      int width;
      int height;
      if (other.x1 >= this.x1 && other.x1 <= this.x2) {
        width = Math.abs(other.x1 - this.x2);
        x1 = other.x1;
      } else {
        width = Math.abs(this.x1 - other.x2);
        x1 = other.x2 - width;
      }
      if (other.y1 >= this.y1 && other.y1 <= this.y2) {
        height = Math.abs(other.y1 - this.y2);
        y1 = other.y1;
      } else {
        height = Math.abs(this.y1 - other.y2);
        y1 = other.y2 - height;
      }
      return Optional.of(new Rectangle(new Point(x1, y1), width, height));
    } else {
      return Optional.empty();
    }
  }

}

