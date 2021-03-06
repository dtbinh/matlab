function x = leontief(C, d)
%leontief input-output model
%   x = Amount produced; Cx = Intermediate demand; d = Final demand;
%   x = Cx + d
%   x - Cx = d
%   Ix - Cx = d
%   (I - C)x = d
%   (I - C)^-1 * (I - C)x = (I - C)^-1 * d
%   Ix = (I - C)^-1 * d
%   x = (I - C)^-1 * d is equivalent to the function below

n = numel(d);
x = (eye(n) - C)\d;

end
